package hei.school.dish_application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.StockValue;
import hei.school.dish_application.entity.UnitType;

@Component
public class StockRepository {

    private DataSource dataSource;

    public StockRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public StockValue getStockValues(int id, Instant t, UnitType unit ){
        try (Connection conn = dataSource.getConnection()){
            String sql = """
                SELECT 
                    SUM(CASE
                        WHEN st.type = 'IN' THEN st.quantity
                        WHEN st.type = 'OUT' THEN -st.quantity ELSE 0 END) as stockValue , 
                    st.unit
                FROM INGREDIENT i  
                LEFT JOIN STOCKMOVEMENT st 
                ON i.id = st.id_ingredient 
                WHERE st.creation_datetime <= ?
                    AND UNIT = ?::unit_type
                    AND id_ingredient = ?
                GROUP BY st.unit
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.from(t));
        ps.setString(2, unit.name());
        ps.setInt(3, id);
        ResultSet rs = ps.executeQuery();
        StockValue stockValue = new StockValue();

        if (rs.next()) {
            Long values = rs.getLong("stockValue");
            String getUnitType = rs.getString("unit");
            UnitType unitType = UnitType.valueOf(getUnitType);

            stockValue.setValue(values);
            stockValue.setUnit(unitType);
            
        }
        return stockValue;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    
    
}
