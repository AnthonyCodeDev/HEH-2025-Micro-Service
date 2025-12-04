package be.heh.recommendationservice.service;

import be.heh.recommendationservice.model.Recommendation;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecommendationProducer {
    private final ObjectProvider<KafkaTemplate<String, Recommendation>> kafkaTemplateProvider;

    public RecommendationProducer(ObjectProvider<KafkaTemplate<String, Recommendation>> kafkaTemplateProvider) {
        this.kafkaTemplateProvider = kafkaTemplateProvider;
    }

    public void sendRecommendation(Recommendation recommendation) {
        KafkaTemplate<String, Recommendation> kafkaTemplate = kafkaTemplateProvider.getIfAvailable();
        if (kafkaTemplate == null) {
            return; // Kafka not configured; nothing to publish
        }
        kafkaTemplate.send("recommendations-topic", recommendation);
    }
}
