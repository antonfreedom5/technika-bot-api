package com.yadchenko.botspectehnika.services;

import com.yadchenko.botspectehnika.entities.Attachment;
import com.yadchenko.botspectehnika.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    public List<Attachment> getAttachmentsByMachineId(Long machineId) {
        return attachmentRepository.findAllByMachineId(machineId);
    }

    public Attachment getById(Long id) {
        return attachmentRepository.getById(id);
    }
}
