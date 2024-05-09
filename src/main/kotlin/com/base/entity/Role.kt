package com.base.entity

import com.base.entity.base.AuditingBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import lombok.RequiredArgsConstructor

@Entity
@Table(name = "roles")
@Data
class Role(
    var name: String,
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    var users: List<User> = mutableListOf()
) : AuditingBaseEntity()
