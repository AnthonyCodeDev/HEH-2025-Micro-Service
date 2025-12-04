package be.heh.productcompositeservice.model;

import java.util.List;

public class ProductAggregate {
    private Product product;
    private List<Review> reviews;
    private List<Recommendation> recommendations;

    public ProductAggregate() {
    }

    public ProductAggregate(Product product, List<Review> reviews, List<Recommendation> recommendations) {
        this.product = product;
        this.reviews = reviews;
        this.recommendations = recommendations;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
