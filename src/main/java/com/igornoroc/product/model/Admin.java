package com.igornoroc.product.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull @NotEmpty
    private String login;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String role;
}
