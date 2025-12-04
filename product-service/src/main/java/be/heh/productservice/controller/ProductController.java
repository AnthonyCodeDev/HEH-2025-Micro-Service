package be.heh.productservice.controller;

import be.heh.productservice.model.Product;
import be.heh.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
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

    // API pour créer un produit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // API pour supprimer un produit
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable int productId) {
        return productService.deleteProduct(productId);
    }
}
