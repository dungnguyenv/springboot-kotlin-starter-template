package com.base.dto.auth

data class AuthenticationRequest(
    val username: String,
    val password: String,
)