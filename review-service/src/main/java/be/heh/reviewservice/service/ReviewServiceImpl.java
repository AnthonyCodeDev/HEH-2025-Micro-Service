package be.heh.reviewservice.service;

import be.heh.reviewservice.Modele.Review;
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
                .map(entity -> {
                    Review review = mapper.entityToApi(entity);
                    review.setServiceAddress("localhost:7004");
                    return review;
                });
    }

    public Mono<Review> createReview(Review review) {
        ReviewEntity entity = mapper.apiToEntity(review);
        return repository.save(entity)
                .map(savedEntity -> {
                    Review savedReview = mapper.entityToApi(savedEntity);
                    savedReview.setServiceAddress("localhost:7004");
                    return savedReview;
                });
    }

    public Mono<Void> deleteReviews(int productId) {
        return repository.findByProductId(productId)
                .flatMap(repository::delete)
                .then();
    }
}
