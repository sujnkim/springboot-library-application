package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.calculator.response.CalcResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(
            @RequestBody CalculatorMultiplyRequest request
    ) {
        return request.getNumber1() * request.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public CalcResponse calculateTwoNumbers(
            @RequestParam int num1,
            @RequestParam int num2
    ) {
        return new CalcResponse(
                num1 + num2,
                num1 - num2,
                num1 * num2
        );
    }

}
