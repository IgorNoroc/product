package com.igornoroc.product.rest;

import com.igornoroc.product.model.Language;
import com.igornoroc.product.model.PriceType;
import com.igornoroc.product.model.Product;
import com.igornoroc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductClientService {
    private final ProductService productService;

    @GetMapping("/find/{id}/{language}/{priceType}")
    public Product getProductById(@PathVariable @NotNull Long id,
                                  @PathVariable @NotNull Language language,
                                  @PathVariable @NotNull PriceType priceType) {
        return productService.findById(id, language, priceType);
    }

    @GetMapping("/all/{language}/{priceType}")
    public Page<Product> getAll(@PageableDefault Pageable pageable,
                                @PathVariable @NotNull Language language,
                                @PathVariable @NotNull PriceType priceType) {
        return productService.findAll(language, priceType, pageable);
    }

    @GetMapping("/all_by_name/{name}/{language}/{priceType}")
    public Page<Product> getAllByName(@PageableDefault Pageable pageable,
                                      @PathVariable @NotNull String name,
                                      @PathVariable @NotNull Language language,
                                      @PathVariable @NotNull PriceType priceType) {
        return productService.findAllByName(name, language, priceType, pageable);
    }

    @GetMapping("/all_by_description/{description}/{language}/{priceType}")
    public Page<Product> getAllByDescription(@PageableDefault Pageable pageable,
                                             @PathVariable @NotNull String description,
                                             @PathVariable @NotNull Language language,
                                             @PathVariable @NotNull PriceType priceType) {
        return productService.findAllByDescription(description, language, priceType, pageable);
    }

}
