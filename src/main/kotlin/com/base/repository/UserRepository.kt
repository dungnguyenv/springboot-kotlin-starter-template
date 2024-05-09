package com.base.repository

import com.base.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String, pageable: Pageable): Page<User>

    fun findByUsername(username: String): Optional<User>

    @Query("SELECT u.active FROM User u WHERE u.username = :username")
    fun isActiveByUsername(username: String): Optional<Boolean>
}