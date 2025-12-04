package be.heh.recommendationservice.controller;

import be.heh.recommendationservice.model.Recommendation;
import be.heh.recommendationservice.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        return service.createRecommendation(recommendation);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRecommendations(@PathVariable int productId) {
        return service.deleteRecommendations(productId);
    }
}
