package main.java.be.heh.recommendationservice.messaging;

import be.heh.recommendationservice.model.Recommendation;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class RecommendationConsummer {
    @KafkaListener(topics = "recommendations-topic", groupId = "recommendation-service")
    public void listen(Recommendation recommendation) {
        System.out.println("Received recommendation: " + recommendation);
    }
}
