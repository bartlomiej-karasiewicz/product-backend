package com.task.productbackend.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {

}
