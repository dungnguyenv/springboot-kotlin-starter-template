package com.base.config.auditting

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import java.util.*

class SpringSecurityAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        val context = SecurityContextHolder.getContext() ?: return Optional.of("System")

        val authentication = context.authentication
        if (authentication != null) {
            val username = when (val principal = authentication.principal) {
                is User -> principal.username
                else -> principal.toString()
            }
            return Optional.of(username)
        }

        return Optional.of("System")
    }
}
