package com.base.dto.product

import java.util.UUID

data class ProductRequest(
    val id: UUID,
    val name: String,
    val price: Float,
)