package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    long countAllByName(String name);

    List<Fruit> findByPriceGreaterThanEqual(long price);

    List<Fruit> findByPriceLessThanEqual(long price);

    List<Fruit> findAllByNameAndIsSold(String name, boolean isSold);

}
