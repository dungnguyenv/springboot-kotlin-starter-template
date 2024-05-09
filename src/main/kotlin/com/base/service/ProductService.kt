package com.base.service

import com.base.dto.product.ProductRequest
import com.base.entity.Product
import com.base.exception.ResourceNotFoundException
import com.base.repository.ProductRepository
import org.springframework.stereotype.Service


@Service
class ProductService(val productRepository: ProductRepository) {
    fun createProduct(dto: ProductRequest) {
        val product = Product(
            name = dto.name,
            price = dto.price
        )

        productRepository.save(product)
    }

    fun updateProduct(dto: ProductRequest) {
        val product =
            productRepository.findById(dto.id).orElseThrow { ResourceNotFoundException("Product ${dto.id} Not Found") }

        product.name = dto.name
        product.price = dto.price

        productRepository.save(product)
    }

}