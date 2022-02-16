package com.igornoroc.product.service;


import com.igornoroc.product.model.Product;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    Product findById(Long id);
}
