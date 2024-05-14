package com.group.libraryapp.domain.fruit;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private LocalDate warehousingDate;

    @Column(nullable = false)
    private boolean isSold;


    protected Fruit() {

    }

    public Fruit(String name, long price, LocalDate warehousingDate, boolean isSold) {
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
        this.isSold = false;
    }

}
