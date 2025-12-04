package be.heh.recommendationservice.service;

import be.heh.recommendationservice.mapper.RecommendationMapper;
import be.heh.recommendationservice.model.Recommendation;
import be.heh.recommendationservice.persistence.RecommendationEntity;
import be.heh.recommendationservice.persistence.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;
    private final RecommendationMapper mapper;

    @Autowired
    public RecommendationService(RecommendationRepository repository, RecommendationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<Recommendation> getRecommendations(int productId) {
        return repository.findByProductId(productId)
                .map(mapper::entityToApi);
    }

    public Mono<Recommendation> createRecommendation(Recommendation recommendation) {
        RecommendationEntity entity = mapper.apiToEntity(recommendation);
        return repository.save(entity).map(mapper::entityToApi);
    }

    public Mono<Void> deleteRecommendations(int productId) {
        return repository.findByProductId(productId)
                .flatMap(repository::delete)
                .then();
    }
}
