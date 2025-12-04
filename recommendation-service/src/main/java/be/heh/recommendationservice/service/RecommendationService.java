package be.heh.recommendationservice.service;

import be.heh.recommendationservice.model.Recommendation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RecommendationService {
    public Flux<Recommendation> getRecommendations(int productId) {
        return Flux.just(
                new Recommendation(productId, 1, "Kevin", 5, "Excellent produit !"),
                new Recommendation(productId, 2, "Alice", 4, "Très bon mais améliorable."),
                new Recommendation(productId, 3, "Marc", 3, "Correct sans plus.")
        );

    }
}
