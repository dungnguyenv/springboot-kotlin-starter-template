package com.base.service.auth

import com.base.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.base.entity.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User $username Not Found") }
        return mapToUserDetails(applicationUser)
    }


    private fun mapToUserDetails(user: ApplicationUser): UserDetails =
        User.builder()
            .username(user.username)
            .password(user.password)
            .roles(*user.roles.map { it.name }.toTypedArray())
            .build()
}