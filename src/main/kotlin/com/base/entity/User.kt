package com.base.entity

import com.base.entity.base.AuditingBaseEntity
import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.RequiredArgsConstructor

@Entity
@Table(name = "users")
@Data
class User(
    var name: String,
    var email: String,
    var username: String,
    var password: String,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    ) var roles: List<Role> = mutableListOf(),
    @Column(name = "active", nullable = false)
    var active: Boolean = true
) : AuditingBaseEntity()
