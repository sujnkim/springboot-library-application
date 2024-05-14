package com.group.libraryapp.repository.fruit;


import com.group.libraryapp.dto.fruit.response.FruitResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FruitMySqlRepository implements FruitJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveFruit(String name, Long price, LocalDate warehousingDate) {
        String sql = "INSERT INTO fruit(name, price, warehousing_date) VALUES(?,?,?)";
        jdbcTemplate.update(sql, name, price, warehousingDate);
    }

    @Override
    public List<FruitResponse> getFruits() {
        String sql = "SELECT * FROM fruit";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long price = rs.getLong("price");
            LocalDate warehousingDate = rs.getDate("warehousing_date").toLocalDate();
            boolean isSold = rs.getBoolean("is_sold");
            return new FruitResponse(id, name, price, warehousingDate, isSold);
        });
    }

    @Override
    public long getSalesAmount(String name) {
        String salesSql = "SELECT SUM(price) FROM fruit WHERE name = ?" +
                "GROUP BY is_sold HAVING is_sold = true";
        return jdbcTemplate.query(salesSql, (rs, rowNum) ->
                rs.getLong("SUM(price)"), name).get(0);
    }


    @Override
    public long getNotSalesAmount(String name) {
        String notSaleSql = "SELECT SUM(price) FROM fruit WHERE name = ?" +
                "GROUP BY is_sold HAVING is_sold = false";
        return jdbcTemplate.query(notSaleSql, (rs, rowNum) ->
                rs.getLong("SUM(price)"), name).get(0);
    }

    @Override
    public boolean isFruitNotExist(long id) {
        String reqdSql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(reqdSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public void updateFruitToSold(long id) {
        String sql = "UPDATE fruit SET is_sold = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}

