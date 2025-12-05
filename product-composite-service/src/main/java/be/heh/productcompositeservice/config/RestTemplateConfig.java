package be.heh.productcompositeservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // Cette annotation est nécessaire pour la découverte de services via Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}