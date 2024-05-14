package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSellRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.service.fruit.FruitServiceV1;
import com.group.libraryapp.service.fruit.FruitServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FruitController {

    private final FruitServiceV2 fruitService;

    public FruitController(FruitServiceV2 fruitService) {
        this.fruitService = fruitService;
    }


    @PostMapping("/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        fruitService.saveFruit(request);
    }


    @GetMapping("/fruit")
    public List<FruitResponse> getFruits() {
        return fruitService.getFruits();
    }


    @PutMapping("/fruit")
    public void sellFruit(@RequestBody FruitSellRequest request) {
        fruitService.sellFruit(request);
    }


    @GetMapping("/fruit/stat")
    public FruitStatResponse statFruit(@RequestParam String name) {
        return fruitService.statFruit(name);
    }

}
