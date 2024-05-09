package com.base.controller

import com.base.dto.product.ProductRequest
import com.base.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/public/products")
class ProductController(
    val productService: ProductService
) {

    @PostMapping
    fun createProduct(
        dto: ProductRequest
    ) {
        productService.createProduct(dto)
    }

    @PutMapping
    fun updateProduct(
        dto: ProductRequest
    ) {
        productService.updateProduct(dto)
    }
}