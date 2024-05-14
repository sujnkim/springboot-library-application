package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitResponse;

import java.time.LocalDate;
import java.util.List;

public interface FruitJdbcRepository {

    public void saveFruit(String name, Long price, LocalDate warehousingDate);

    public List<FruitResponse> getFruits();

    public long getSalesAmount(String name);

    public long getNotSalesAmount(String name);

    public boolean isFruitNotExist(long id);

    public void updateFruitToSold(long id);
}
