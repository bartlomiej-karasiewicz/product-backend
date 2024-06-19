package com.task.productbackend.domain.product.service;

import com.task.productbackend.domain.product.Product;
import com.task.productbackend.domain.product.port.out.ProductRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> getProducts() {
        return productRepositoryPort.findAll();
    }

    public Optional<Product> getProduct(String id) {
        return productRepositoryPort.findById(id);
    }

    public void deleteProduct(String id) {
        productRepositoryPort.deleteProduct(id);
    }

    public void addProduct(Product product) {
        productRepositoryPort.addProduct(product);
    }
}
