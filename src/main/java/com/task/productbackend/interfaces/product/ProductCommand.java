package com.task.productbackend.interfaces.product;

import java.math.BigDecimal;

public record ProductCommand(String name, BigDecimal price) {

}
