package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSellRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FruitController {
    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostMapping("/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        String sql = "INSERT INTO fruit(name, price, warehousing_date) VALUES(?,?,?)";
        jdbcTemplate.update(sql,
                request.getName(),
                request.getPrice(),
                request.getWarehousingDate()
        );
    }


    @GetMapping("/fruit")
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


    @PutMapping("/fruit")
    public void sellFruit(@RequestBody FruitSellRequest request) {
        String reqdSql = "SELECT * FROM fruit WHERE id = ?";
        boolean isFruitNotExist = jdbcTemplate.query(reqdSql,
                (rs, rowNum) -> 0,
                request.getId()).isEmpty();

        if (isFruitNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE fruit SET is_sold = true WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }


    @GetMapping("/fruit/stat")
    public FruitStatResponse statFruit(@RequestParam String name) {
        String salesSql = "SELECT price FROM fruit WHERE name = ? and is_sold = true";
        List<Long> salePrices = jdbcTemplate.query(salesSql, (rs, rowNum) -> {
            return rs.getLong("price");
        }, name);
        long salesAmount = salePrices.stream()
                .mapToLong(i -> i)
                .sum();


        String notSaleSql = "SELECT price FROM fruit WHERE name = ? and is_sold = false";
        List<Long> notSalePrices = jdbcTemplate.query(notSaleSql, (rs, rowNum) -> {
            return rs.getLong("price");
        }, name);
        long notSalesAmount = notSalePrices.stream()
                .mapToLong(i -> i)
                .sum();

        return new FruitStatResponse(salesAmount, notSalesAmount);

    }

}