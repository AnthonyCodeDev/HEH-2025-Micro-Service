package com.example.productservice.service;

import com.example.productservice.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> getProduct(int productId);
}
