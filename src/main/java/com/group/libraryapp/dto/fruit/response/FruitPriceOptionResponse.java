package com.group.libraryapp.dto.fruit.response;


import com.group.libraryapp.domain.fruit.Fruit;

import java.time.LocalDate;


public class FruitPriceOptionResponse {

    private String name;
    private long price;
    private LocalDate warehousingDate;

    public FruitPriceOptionResponse(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.warehousingDate = fruit.getWarehousingDate();
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}
