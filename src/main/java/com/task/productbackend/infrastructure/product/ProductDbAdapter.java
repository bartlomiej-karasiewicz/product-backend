package com.task.productbackend.infrastructure.product;

import com.task.productbackend.domain.product.Product;
import com.task.productbackend.domain.product.port.out.ProductRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDbAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return productJpaRepository.findById(id)
                .map(ProductEntity::toProduct);
    }

    @Override
    public void deleteProduct(String id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productJpaRepository.save(ProductEntity.fromProduct(product));
    }
}

