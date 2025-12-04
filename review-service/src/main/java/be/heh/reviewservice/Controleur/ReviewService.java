package be.heh.reviewservice.Controleur;

import be.heh.reviewservice.Modele.Review;
import be.heh.reviewservice.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReviewService {

    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewService(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Implémentation de l'API: getReviews(int productId)
     * URL exemple: http://localhost:7004/review?productId=1
     */
    @GetMapping(value = "/review", produces = "application/json")
    public Flux<Review> getReviews(@RequestParam(value = "productId", required = true) int productId) {
        return reviewService.getReviews(productId);
    }

    /**
     * API pour créer un review
     */
    @PostMapping(value = "/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Review> createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    /**
     * API pour supprimer tous les reviews d'un produit
     */
    @DeleteMapping(value = "/review")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteReviews(@RequestParam(value = "productId", required = true) int productId) {
        return reviewService.deleteReviews(productId);
    }
}
