package com.task.productbackend.domain.product.adapter;

import com.task.productbackend.domain.product.Product;
import com.task.productbackend.domain.product.exception.NotFoundProductException;
import com.task.productbackend.domain.product.port.in.ProductServicePort;
import com.task.productbackend.domain.product.service.ProductService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class ProductServiceAdapter implements ProductServicePort {

    private final ProductService productService;

    public ProductServiceAdapter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Override
    public Product getProduct(String id) {
        return productService.getProduct(id)
                .orElseThrow(() -> new NotFoundProductException("Not found product with id: " + id));
    }

    @Override
    public void deleteProduct(String id) {
        try {
            productService.deleteProduct(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundProductException("Not found product to deletion with id " + id);
        }
    }

    @Override
    public void addProduct(Product product) {
        productService.addProduct(product);
    }
}
