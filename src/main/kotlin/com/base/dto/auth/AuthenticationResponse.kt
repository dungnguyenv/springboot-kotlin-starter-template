package com.base.dto.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)