package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FruitMemoryRepository implements FruitRepository {

    @Override
    public void saveFruit(String name, Long price, LocalDate warehousingDate) {

    }

    @Override
    public List<FruitResponse> getFruits() {
        return null;
    }

    @Override
    public long getSalesAmount(String name) {
        return 0;
    }

    @Override
    public long getNotSalesAmount(String name) {
        return 0;
    }

    @Override
    public boolean isFruitNotExist(long id) {
        return false;
    }

    @Override
    public void updateFruitToSold(long id) {

    }
}
