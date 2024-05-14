package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSellRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitPriceOptionResponse;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FruitServiceV2 {

    private final FruitRepository fruitRepository;

    public FruitServiceV2(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.save(new Fruit(request.getName(), request.getPrice(), request.getWarehousingDate()));
    }


    public List<FruitResponse> getFruits() {
        return fruitRepository.findAll().stream()
                .map(fruit -> new FruitResponse(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getPrice(),
                        fruit.getWarehousingDate(),
                        fruit.isSold()))
                .toList();
    }


    public FruitStatResponse statFruit(String name) {
        long salesAmount = fruitRepository.findAllByNameAndIsSold(name, true).stream()
                .mapToLong(fruit -> fruit.getPrice())
                .sum();

        long notSalesAmount = fruitRepository.findAllByNameAndIsSold(name, false).stream()
                .mapToLong(fruit -> fruit.getPrice())
                .sum();

        return new FruitStatResponse(salesAmount, notSalesAmount);
    }


    public FruitCountResponse getFruitCount(String name) {
        return new FruitCountResponse(fruitRepository.countAllByName(name));
    }


    public List<FruitPriceOptionResponse> getFruitsByPriceOption(String option, long standardPrice) {
        List<Fruit> fruits;

        if (option.equals("GTE")) {
            fruits = fruitRepository.findByPriceGreaterThanEqual(standardPrice);
        } else if (option.equals("LTE")) {
            fruits = fruitRepository.findByPriceLessThanEqual(standardPrice);
        } else {
            throw new IllegalArgumentException();
        }

        return fruits.stream()
                .filter(fruit -> fruit.isSold() == false)
                .map(fruit -> new FruitPriceOptionResponse(fruit))
                .toList();
    }


    public void sellFruit(FruitSellRequest request) {
        Fruit fruit = fruitRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        fruit.updateSoldState();
        fruitRepository.save(fruit);
    }

}
