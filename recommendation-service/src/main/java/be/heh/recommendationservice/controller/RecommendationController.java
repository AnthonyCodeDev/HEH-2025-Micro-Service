package main.java.be.heh.recommendationservice.controller;

import main.java.be.heh.recommendationservice.model.Recommendation;
import main.java.be.heh.recommendationservice.service.RecommendationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


//Ici, je fais mon controller

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public Flux<Recommendation> getRecommendations(@PathVariable int productId) {
        return service.getRecommendations(productId);
    }
}
