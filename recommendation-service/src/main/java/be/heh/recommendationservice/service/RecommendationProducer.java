package main.java.be.heh.recommendationservice.service;

import be.heh.recommendationservice.model.Recommendation;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class RecommendationProducer {
    private final KafkaTemplate<String, Recommendation> kafkaTemplate;

    public RecommendationProducer(KafkaTemplate<String, Recommendation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRecommendation(Recommendation recommendation) {
        kafkaTemplate.send("recommendations-topic", recommendation);
    }
}
