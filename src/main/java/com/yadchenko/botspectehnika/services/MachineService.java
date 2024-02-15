package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.entities.Machine;
import com.yadchenko.botspectehnika.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;

    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    public Machine getById(Long id) {
        return machineRepository.getMachineById(id);
    }
}
