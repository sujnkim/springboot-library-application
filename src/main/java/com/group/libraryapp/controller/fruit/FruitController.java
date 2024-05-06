package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
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

}
