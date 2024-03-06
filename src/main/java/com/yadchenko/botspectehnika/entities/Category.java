package com.yadchenko.botspectehnika.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String imgUrl;

    private Integer price;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Machine> machines = new HashSet<>();
}
