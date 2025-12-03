package be.heh.productservice.service;

import be.heh.productservice.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Mono<Product> getProduct(int productId) {
        // Pas de base de données : on renvoie un produit "dummy"
        return Mono.just(new Product(productId, "Product " + productId, 123));
    }
}
