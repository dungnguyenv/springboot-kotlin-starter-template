package com.base.entity

import com.base.entity.base.AuditingBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.Data


@Entity
@Table(name = "products")
@Data
class Product(
    var name: String,
    var price: Float,
) : AuditingBaseEntity()