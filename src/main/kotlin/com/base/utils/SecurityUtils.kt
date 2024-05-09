package com.base.utils

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails


fun getCurrentUserDetails(): UserDetails? {
    val authentication: Authentication? = SecurityContextHolder.getContext().authentication

    if (authentication != null && authentication.isAuthenticated) {
        val principal = authentication.principal
        if (principal is UserDetails) {
            return principal
        }
    }

    return null
}
