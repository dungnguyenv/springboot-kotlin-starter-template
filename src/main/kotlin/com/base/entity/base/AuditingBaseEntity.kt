package com.base.entity.base

import jakarta.persistence.*
import lombok.RequiredArgsConstructor
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AuditingBaseEntity : BaseEntity() {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    open var createdAt: Instant? = null

    @LastModifiedDate
    @Column(nullable = false)
    open var updatedAt: Instant? = null

    @LastModifiedBy
    @Column(nullable = false)
    open var modifiedBy: String? = null

    @CreatedBy
    @Column(nullable = false, updatable = false)
    open var createdBy: String? = null
}
