package be.heh.productcompositeservice.service;

import be.heh.productcompositeservice.model.Product;
import be.heh.productcompositeservice.model.ProductAggregate;
import be.heh.productcompositeservice.model.Recommendation;
import be.heh.productcompositeservice.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCompositeService {

    private final Logger log = LoggerFactory.getLogger(ProductCompositeService.class);
    private final RestTemplate restTemplate;
    private final String productServiceUrl;
    private final String reviewServiceUrl;
    private final String recommendationServiceUrl;

    public ProductCompositeService(RestTemplate restTemplate,
                                   @Value("${product.service.url:http://localhost:7002}") String productServiceUrl,
                                   @Value("${review.service.url:http://localhost:7004}") String reviewServiceUrl,
                                   @Value("${recommendation.service.url:http://localhost:7003}") String recommendationServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
        this.reviewServiceUrl = reviewServiceUrl;
        this.recommendationServiceUrl = recommendationServiceUrl;
    }

    /**
     * Récupère les informations agrégées d'un produit
     */
    public ProductAggregate getProductAggregate(int productId) {
        // 1. Récupérer les informations du produit
        Product product = restTemplate.getForObject(
                productServiceUrl + "/product/" + productId,
                Product.class
        );

        // 2. Récupérer les avis (reviews)
        Review[] reviewsArray = restTemplate.getForObject(
                reviewServiceUrl + "/review?productId=" + productId,
                Review[].class
        );
        List<Review> reviews = reviewsArray != null ? Arrays.asList(reviewsArray) : List.of();

        // 3. Récupérer les recommandations
        Recommendation[] recommendationsArray = restTemplate.getForObject(
                recommendationServiceUrl + "/recommendations/" + productId,
                Recommendation[].class
        );
        List<Recommendation> recommendations = recommendationsArray != null ?
                Arrays.asList(recommendationsArray) : List.of();

        // 4. Agréger toutes les données
        return new ProductAggregate(product, reviews, recommendations);
    }

    /**
     * Crée un produit composite avec ses reviews et recommandations
     */
    public void createCompositeProduct(ProductAggregate productAggregate) {
        if (productAggregate == null || productAggregate.getProduct() == null) {
            log.warn("Aucun aggregate produit fourni pour la création");
            return;
        }

        // 1. Créer le produit
        restTemplate.postForObject(
                productServiceUrl + "/product",
                productAggregate.getProduct(),
                Product.class
        );

        // 2. Créer les reviews
        if (productAggregate.getReviews() != null) {
            productAggregate.getReviews().forEach(review -> {
                restTemplate.postForObject(
                        reviewServiceUrl + "/review",
                        review,
                        Review.class
                );
            });
        }

        // 3. Créer les recommandations
        if (productAggregate.getRecommendations() != null) {
            productAggregate.getRecommendations().forEach(recommendation -> {
                restTemplate.postForObject(
                        recommendationServiceUrl + "/recommendations",
                        recommendation,
                        Recommendation.class
                );
            });
        }
    }

    /**
     * Supprime un produit composite avec tous ses reviews et recommandations
     */
    public void deleteCompositeProduct(int productId) {
        // 1. Supprimer les reviews
        restTemplate.delete(reviewServiceUrl + "/review?productId=" + productId);

        // 2. Supprimer les recommandations
        restTemplate.delete(recommendationServiceUrl + "/recommendations/" + productId);

        // 3. Supprimer le produit
        restTemplate.delete(productServiceUrl + "/product/" + productId);
    }
}
