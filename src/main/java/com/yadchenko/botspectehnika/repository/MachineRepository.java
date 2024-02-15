package com.yadchenko.botspectehnika.repository;

import com.yadchenko.botspectehnika.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    Machine getMachineById(Long id);
}
