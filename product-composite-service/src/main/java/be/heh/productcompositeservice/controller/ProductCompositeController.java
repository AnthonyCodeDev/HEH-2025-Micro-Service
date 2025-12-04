package be.heh.productcompositeservice.controller;

import be.heh.productcompositeservice.model.ProductAggregate;
import be.heh.productcompositeservice.service.ProductCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product-composite")
public class ProductCompositeController {

    @Autowired
    private ProductCompositeService productCompositeService;

    /**
     * API pour récupérer les informations complètes d'un produit
     * incluant les avis et les recommandations
     * 
     * Exemple: GET http://localhost:7001/product-composite/1
     */
    @GetMapping("/{productId}")
    public ProductAggregate getProduct(@PathVariable int productId) {
        return productCompositeService.getProductAggregate(productId);
    }

    /**
     * API pour créer un produit composite avec ses reviews et recommandations
     * 
     * Exemple: POST http://localhost:7001/product-composite
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAggregate createCompositeProduct(@RequestBody ProductAggregate productAggregate) {
        productCompositeService.createCompositeProduct(productAggregate);
        return productAggregate;
    }

    /**
     * API pour supprimer un produit composite avec tous ses reviews et recommandations
     * 
     * Exemple: DELETE http://localhost:7001/product-composite/1
     */
    @DeleteMapping("/{productId}")
    public Map<String, String> deleteCompositeProduct(@PathVariable int productId) {
        productCompositeService.deleteCompositeProduct(productId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product composite deleted successfully");
        response.put("productId", String.valueOf(productId));
        return response;
    }
}
