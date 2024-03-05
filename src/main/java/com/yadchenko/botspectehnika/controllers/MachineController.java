package com.yadchenko.botspectehnika.controllers;

import com.yadchenko.botspectehnika.entities.Machine;
import com.yadchenko.botspectehnika.services.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {
    private final MachineService machineService;

    @GetMapping("/all")
    List<Machine> getAll(@RequestHeader(value="User-Id") String userId) {
        log.info("User with id {} opened the bot", userId);
        return machineService.getAll();
    }
}
