package com.onlinegroceryshopping.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;

    private String city;

    private String streetAddress;

    private String houseNumber;

    private String zipCode;

    @OneToOne(mappedBy = "address")
    private User user;
}
