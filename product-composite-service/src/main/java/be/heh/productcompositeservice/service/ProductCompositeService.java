package be.heh.productcompositeservice.service;

import be.heh.productcompositeservice.model.Product;
import be.heh.productcompositeservice.model.ProductAggregate;
import be.heh.productcompositeservice.model.Recommendation;
import be.heh.productcompositeservice.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCompositeService {

    @Autowired
    private RestTemplate restTemplate;

    // URLs des services core
    private static final String PRODUCT_SERVICE_URL = "http://localhost:7002/product/";
    private static final String REVIEW_SERVICE_URL = "http://localhost:7004/review?productId=";
    private static final String RECOMMENDATION_SERVICE_URL = "http://localhost:7003/recommendations/";

    /**
     * Récupère les informations agrégées d'un produit
     * en interrogeant les trois services core via RestTemplate
     */
    public ProductAggregate getProductAggregate(int productId) {
        // 1. Récupérer les informations du produit
        Product product = restTemplate.getForObject(
                PRODUCT_SERVICE_URL + productId,
                Product.class
        );

        // 2. Récupérer les avis (reviews)
        Review[] reviewsArray = restTemplate.getForObject(
                REVIEW_SERVICE_URL + productId,
                Review[].class
        );
        List<Review> reviews = reviewsArray != null ? Arrays.asList(reviewsArray) : List.of();

        // 3. Récupérer les recommandations
        Recommendation[] recommendationsArray = restTemplate.getForObject(
                RECOMMENDATION_SERVICE_URL + productId,
                Recommendation[].class
        );
        List<Recommendation> recommendations = recommendationsArray != null ? 
                Arrays.asList(recommendationsArray) : List.of();

        // 4. Agréger toutes les données
        return new ProductAggregate(product, reviews, recommendations);
    }
}
