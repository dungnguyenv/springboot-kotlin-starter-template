package com.base.repository

import com.base.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByName(name: String): Optional<Role>

    fun findByNameIn(names: List<String>): List<Role>
}