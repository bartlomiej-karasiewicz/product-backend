package com.task.productbackend.interfaces.product;

import com.task.productbackend.domain.product.Product;
import com.task.productbackend.domain.product.exception.NotFoundProductException;
import com.task.productbackend.domain.product.port.in.ProductServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping
    public List<ProductQuery> getProducts() {
        return productServicePort.getProducts()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductQuery getProduct(@PathVariable(name = "id") String id) {
        final Product product = productServicePort.getProduct(id);
        return toDto(product);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductCommand command) {
        productServicePort.addProduct(new Product(command.name(), command.price()));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productServicePort.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundProductException.class})
    public ResponseEntity<String> handleException(Exception exception) {
        log.warn("Not found product" + exception.getMessage());
        return ResponseEntity.notFound().build();
    }

    private  ProductQuery toDto(Product product) {
        return ProductQuery.builder()
                .name(product.getName())
                .id(product.getId())
                .price(product.getPrice())
                .build();
    }
}
