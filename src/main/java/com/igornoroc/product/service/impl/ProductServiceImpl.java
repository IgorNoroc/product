package com.igornoroc.product.service.impl;

import com.igornoroc.product.exception.ProductHaveAnotherLanguageOrPriceTypeException;
import com.igornoroc.product.exception.ProductNotFoundException;
import com.igornoroc.product.model.Language;
import com.igornoroc.product.model.PriceType;
import com.igornoroc.product.model.Product;
import com.igornoroc.product.repository.ProductRepository;
import com.igornoroc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Language language, PriceType priceType, Pageable pageable) {
        return productRepository.findAllByLanguageAndPriceType(language, priceType, pageable);
    }

    @Override
    public Page<Product> findAllByName(String name, Language language, PriceType priceType, Pageable pageable) {
        return productRepository.findAllByNameAndLanguageAndPriceType(name, language, priceType, pageable);
    }

    @Override
    public Page<Product> findAllByDescription(String description, Language language, PriceType priceType, Pageable pageable) {
        return productRepository.findAllByDescriptionAndLanguageAndPriceType(description, language, priceType, pageable);
    }

    @Override
    public Product findById(Long id, Language language, PriceType priceType) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        checkIfLanguageOrPriceTypeValid(id, language, priceType, product);
        return product;
    }

    private void checkIfLanguageOrPriceTypeValid(Long id, Language language, PriceType priceType, Product product) {
        if (!(product.getLanguage().equals(language) && product.getPriceType().equals(priceType))) {
            log.error("findById: product with id: {} have incorrect language or priceType", id);
            throw new ProductHaveAnotherLanguageOrPriceTypeException();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.error("product with id:{} not found", id);
            throw new ProductNotFoundException();
        }
    }
}
