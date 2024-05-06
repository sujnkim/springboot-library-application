package com.group.libraryapp.dto.fruit.response;

import java.time.LocalDate;

public class FruitResponse {
    private long id;
    private String name;
    private long price;
    private LocalDate stockedDate;
    private boolean isSold;

    public FruitResponse(long id, String name, long price, LocalDate stockedDate, boolean isSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockedDate = stockedDate;
        this.isSold = isSold;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDate getStockedDate() {
        return stockedDate;
    }

    public boolean isSold() {
        return isSold;
    }
}

