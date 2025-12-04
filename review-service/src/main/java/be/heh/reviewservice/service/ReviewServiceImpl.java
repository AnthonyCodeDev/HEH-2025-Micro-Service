package be.heh.reviewservice.service;

import be.heh.reviewservice.Modele.Review;
import be.heh.reviewservice.exception.AlreadyExistsException;
import be.heh.reviewservice.exception.NotFoundException;
import be.heh.reviewservice.mapper.ReviewMapper;
import be.heh.reviewservice.persistence.ReviewEntity;
import be.heh.reviewservice.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository, ReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<Review> getReviews(int productId) {
        return repository.findByProductId(productId)
                .map(entity -> mapper.entityToApi(entity, "localhost:7004"))
                .switchIfEmpty(Flux.error(new NotFoundException("No reviews found for product id: " + productId)));
    }

    public Mono<Review> createReview(Review review) {
        ReviewEntity entity = mapper.apiToEntity(review);
        return repository.save(entity)
                .map(savedEntity -> mapper.entityToApi(savedEntity, "localhost:7004"))
                .onErrorMap(e -> e.getMessage().contains("Duplicate"), 
                    e -> new AlreadyExistsException("Review already exists for product id: " + review.getProductId() + " and review id: " + review.getReviewId()));
    }

    public Mono<Void> deleteReviews(int productId) {
        return repository.findByProductId(productId)
                .flatMap(repository::delete)
                .then();
    }
}
