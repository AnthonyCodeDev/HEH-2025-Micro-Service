package be.heh.productservice.service;

import be.heh.productservice.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> getProduct(int productId);
    Mono<Product> createProduct(Product product);
    Mono<Void> deleteProduct(int productId);
}
