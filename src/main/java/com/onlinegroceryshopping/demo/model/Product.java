package com.onlinegroceryshopping.demo.model;

import com.onlinegroceryshopping.demo.model.enums.ProductCategory;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(columnDefinition = "varchar", name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "carts_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<ShoppingCart> shoppingCart;
}
