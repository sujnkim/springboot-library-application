package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSellRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitJdbcRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FruitService {

    private final FruitJdbcRepository fruitRepository;

    public FruitService(@Qualifier("fruitMySqlRepository") FruitJdbcRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }


    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.saveFruit(
                request.getName(), request.getPrice(), request.getWarehousingDate()
        );
    }


    public List<FruitResponse> getFruits() {
        return fruitRepository.getFruits();
    }


    public void sellFruit(FruitSellRequest request) {
        if (fruitRepository.isFruitNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        fruitRepository.updateFruitToSold(request.getId());
    }


    public FruitStatResponse statFruit(String name) {
        long salesAmount = fruitRepository.getSalesAmount(name);
        long notSalesAmount = fruitRepository.getNotSalesAmount(name);
        return new FruitStatResponse(salesAmount, notSalesAmount);
    }


}
