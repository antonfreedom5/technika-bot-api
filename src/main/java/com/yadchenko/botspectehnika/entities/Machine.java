package com.yadchenko.botspectehnika.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String imgUrl;

    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "machines_attachments",
            joinColumns = { @JoinColumn(name = "machine_id") },
            inverseJoinColumns = { @JoinColumn(name = "attachment_id") }
    )
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "machines_categories",
            joinColumns = { @JoinColumn(name = "machine_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<Category> categories = new HashSet<>();
}
