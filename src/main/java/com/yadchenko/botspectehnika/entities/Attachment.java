package com.yadchenko.botspectehnika.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String imgUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "attachments")
    private Set<Machine> machines = new HashSet<>();
}
