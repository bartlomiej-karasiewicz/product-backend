package com.task.productbackend.interfaces.product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductQuery(Long id, String name, BigDecimal price) {

}
