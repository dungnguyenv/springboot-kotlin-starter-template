package com.base.repository

import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class RefreshTokenRepository {

    private val tokens = mutableMapOf<String, UserDetails>()

    fun findUserDetailsByToken(token: String): UserDetails =
        tokens[token] ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

    fun save(token: String, userDetails: UserDetails) {
        tokens[token] = userDetails
    }

}