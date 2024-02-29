package com.yadchenko.botspectehnika.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Cloneable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private User employee;
    @ManyToOne
    private Machine machine;
    @ManyToOne
    private Attachment attachment;
    @ManyToOne
    private Category category;
    private Date date;
    private String place;
    private Date orderDate;
    private String phone;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
