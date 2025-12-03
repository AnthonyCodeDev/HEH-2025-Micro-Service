package com.example.productservice.controller;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // API demandée : getProducts(int productId)
    @GetMapping("/{productId}")
    public Mono<Product> getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }
}
