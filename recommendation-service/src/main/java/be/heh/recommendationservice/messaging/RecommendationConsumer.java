package be.heh.recommendationservice.messaging;

import be.heh.recommendationservice.model.Recommendation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RecommendationConsumer {
    @KafkaListener(topics = "recommendations-topic", groupId = "recommendation-service")
    public void listen(Recommendation recommendation) {
        System.out.println("Received recommendation: " + recommendation);
    }
}
