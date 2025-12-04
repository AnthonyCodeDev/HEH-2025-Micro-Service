package be.heh.productcompositeservice.controller;

import be.heh.productcompositeservice.model.ProductAggregate;
import be.heh.productcompositeservice.service.ProductCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
