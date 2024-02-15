package com.yadchenko.botspectehnika.repository;

import com.yadchenko.botspectehnika.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    @Query(value = "SELECT a.id, a.name FROM Attachment a INNER JOIN machines_attachments ma ON a.id = ma.attachment_id INNER JOIN Machine m ON m.id = ma.machine_id WHERE m.id = :machineId", nativeQuery = true)
    List<Attachment> findAllByMachineId(Long machineId);

    @Query("SELECT attachment FROM Attachment attachment JOIN FETCH attachment.machines WHERE attachment.id = :id")
    Attachment getById(Long id);
}
