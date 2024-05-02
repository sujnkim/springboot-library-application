package com.group.libraryapp.dto.calculator.response;

import java.time.DayOfWeek;

public class DayOfWeekResponse {
    private final String dayOfTheWeek;

    public DayOfWeekResponse(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek.toString().substring(0,3);
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
