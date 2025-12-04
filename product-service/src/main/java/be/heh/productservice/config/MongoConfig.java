package be.heh.productservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    @Profile("docker")
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString("mongodb://admin:password@mongodb:27017/product_db?authSource=admin"))
            .build();
        return MongoClients.create(settings);
    }

    @Bean
    @Profile("!docker")
    public MongoClient mongoClientDefault() {
        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString("mongodb://admin:password@localhost:27017/product_db?authSource=admin"))
            .build();
        return MongoClients.create(settings);
    }
}
