package com.group.libraryapp.dto.calculator.response;

public class CalcResponse {
    private final int add;
    private final int minus;
    private final int multiply;

    public CalcResponse(int num1, int num2) {
        this.add = num1 + num2;
        this.minus = num1 - num2;
        this.multiply = num1 * num2;
    }

    public int getAdd() {
        return add;
    }

    public int getMinus() {
        return minus;
    }

    public int getMultiply() {
        return multiply;
    }
}
