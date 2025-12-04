package be.heh.productservice.service;

import be.heh.productservice.mapper.ProductMapper;
import be.heh.productservice.model.Product;
import be.heh.productservice.persistence.ProductEntity;
import be.heh.productservice.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Product> getProduct(int productId) {
        return repository.findByProductId(productId)
                .map(mapper::entityToApi)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found: " + productId)));
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return repository.findByProductId(product.getProductId())
                .flatMap(existingProduct -> 
                    Mono.<Product>error(new RuntimeException("Product already exists: " + product.getProductId()))
                )
                .switchIfEmpty(
                    Mono.defer(() -> {
                        ProductEntity entity = mapper.apiToEntity(product);
                        return repository.save(entity).map(mapper::entityToApi);
                    })
                );
    }

    @Override
    public Mono<Void> deleteProduct(int productId) {
        return repository.findByProductId(productId)
                .flatMap(repository::delete)
                .then();
    }
}
