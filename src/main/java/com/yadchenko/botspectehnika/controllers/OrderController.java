package com.yadchenko.botspectehnika.controllers;

import com.yadchenko.botspectehnika.dto.OrderDto;
import com.yadchenko.botspectehnika.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    void save(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }
}
