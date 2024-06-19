package com.task.productbackend.domain.product.port.out;

import com.task.productbackend.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> findAll();

    Optional<Product> findById(String id);

    void deleteProduct(String id);

    void addProduct(Product product);
}
