package be.heh.reviewservice.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepository extends ReactiveCrudRepository<ReviewEntity, Long> {
    Flux<ReviewEntity> findByProductId(int productId);
}
