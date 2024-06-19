package com.task.productbackend.infrastructure.product;

import com.task.productbackend.domain.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public ProductEntity(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public static ProductEntity fromProduct(Product product) {
        return new ProductEntity(product.getName(), product.getPrice());
    }

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
    }
}
