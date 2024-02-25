package com.yadchenko.botspectehnika.controllers;

import com.yadchenko.botspectehnika.dto.OrderDto;
import com.yadchenko.botspectehnika.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    void save(@RequestBody OrderDto orderDto) {
        log.info("New order! " + orderDto.user().getId());
        orderService.save(orderDto);
    }
}
