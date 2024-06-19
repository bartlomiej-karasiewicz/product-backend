package com.task.productbackend.configuration;

import com.task.productbackend.domain.product.adapter.ProductServiceAdapter;
import com.task.productbackend.domain.product.port.in.ProductServicePort;
import com.task.productbackend.domain.product.port.out.ProductRepositoryPort;
import com.task.productbackend.domain.product.service.ProductService;
import com.task.productbackend.infrastructure.product.ProductDbAdapter;
import com.task.productbackend.infrastructure.product.ProductJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort) {
        return new ProductServiceAdapter(new ProductService(productRepositoryPort));
    }

    @Bean
    ProductRepositoryPort productRepositoryPort(ProductJpaRepository productJpaRepository) {
        return new ProductDbAdapter(productJpaRepository);
    }

}
