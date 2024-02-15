package com.yadchenko.botspectehnika.controllers;

import com.yadchenko.botspectehnika.entities.Machine;
import com.yadchenko.botspectehnika.services.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor
public class MachineController {
    private final MachineService machineService;

    @GetMapping("/all")
    List<Machine> getAll() {
        return machineService.getAll();
    }
}
