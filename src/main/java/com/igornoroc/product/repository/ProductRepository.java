package com.igornoroc.product.repository;

import com.igornoroc.product.model.Language;
import com.igornoroc.product.model.PriceType;
import com.igornoroc.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByLanguageAndPriceType(Language language, PriceType priceType, Pageable pageable);

    Page<Product> findAllByNameAndLanguageAndPriceType(String name, Language language, PriceType priceType, Pageable pageable);

    Page<Product> findAllByDescriptionAndLanguageAndPriceType(String description, Language language, PriceType priceType, Pageable pageable);
}
