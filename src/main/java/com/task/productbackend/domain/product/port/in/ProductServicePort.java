package com.task.productbackend.domain.product.port.in;

import com.task.productbackend.domain.product.Product;

import java.util.List;

public interface ProductServicePort {

    List<Product> getProducts();

    Product getProduct(String id);

    void deleteProduct(String id);

    void addProduct(Product product);
}
