package com.igornoroc.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty
    private String name;
    private String description;
    @NotNull @Positive
    private Integer price;
    @Enumerated(EnumType.STRING) @NotNull
    private PriceType priceType;
    @Enumerated(EnumType.STRING) @NotNull
    private Language language;
    @Column(updatable = false)
    private Date created;
    private Date updated;

    public Product(Long id, String name, String description, Integer price, PriceType priceType, Language language) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceType = priceType;
        this.language = language;
    }

    @PrePersist
    private void setCreated() {
        created = new Date();
    }

    @PreUpdate
    private void setUpdated() {
        updated = new Date();
    }
}
