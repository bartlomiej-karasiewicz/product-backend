package com.task.productbackend.domain.product.adapter

import com.task.productbackend.domain.product.Product
import com.task.productbackend.domain.product.exception.NotFoundProductException
import com.task.productbackend.domain.product.port.in.ProductServicePort
import com.task.productbackend.domain.product.port.out.ProductRepositoryPort
import com.task.productbackend.domain.product.service.ProductService
import spock.lang.Specification

class ProductServiceAdapterTest extends Specification {

    private ProductServicePort productServicePort
    private ProductRepositoryPort productRepositoryPort

    def setup() {
        productRepositoryPort = Mock()
        def productService = new ProductService(productRepositoryPort)
        productServicePort = new ProductServiceAdapter(productService)
    }

    def "should get all products"() {
        given:
        productRepositoryPort.findAll() >> List.of(new Product(1L, "testName", BigDecimal.ONE))

        when:
        def products = productServicePort.getProducts()

        then:
        products.size() == 1
        products.get(0).name == 'testName'
        products.get(0).price == BigDecimal.ONE
    }

    def "should return specific product"() {
        given:
        productRepositoryPort.findById("1") >> Optional.of(new Product(1L, "testName", BigDecimal.ONE))

        when:
        def products = productServicePort.getProduct("1")

        then:
        products.name == 'testName'
        products.price == BigDecimal.ONE
    }

    def "should throw exception"() {
        given:
        productRepositoryPort.findById("1") >> Optional.empty()

        when:
        productServicePort.getProduct("1")

        then:
        thrown(NotFoundProductException.class)
    }

    def "should delete product by id"() {
        when:
        productServicePort.deleteProduct("1")

        then:
        1 * productRepositoryPort.deleteProduct("1")
    }

    def "should add product"() {
        when:
        productServicePort.addProduct(new Product(1L, "testName", BigDecimal.ONE))

        then:
        1 * productRepositoryPort.addProduct(_)
    }
}
