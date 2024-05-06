package com.group.libraryapp.dto.fruit.response;

public class FruitStatResponse {
    private long salesAmount;
    private long notSalesAmount;

    public FruitStatResponse(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public long getNotSalesAmount() {
        return notSalesAmount;
    }
}
