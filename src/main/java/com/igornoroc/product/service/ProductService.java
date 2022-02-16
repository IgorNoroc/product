package com.igornoroc.product.service;

import com.igornoroc.product.model.Language;
import com.igornoroc.product.model.PriceType;
import com.igornoroc.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product saveOrUpdate(Product product);

    Product findById(Long id, Language language, PriceType priceType);

    Page<Product> findAll(Language language, PriceType priceType, Pageable pageable);

    Page<Product> findAllByName(String name, Language language, PriceType priceType, Pageable pageable);

    Page<Product> findAllByDescription(String description, Language language, PriceType priceType, Pageable pageable);

    void delete(Long id);
}
