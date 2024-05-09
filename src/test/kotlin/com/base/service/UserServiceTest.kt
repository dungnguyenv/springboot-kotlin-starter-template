package com.base.service

import com.base.TestUtils
import com.base.entity.Role
import com.base.mapper.UserMapper
import com.base.repository.RoleRepository
import com.base.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.util.ReflectionTestUtils
import java.util.*


@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    val userRepository: UserRepository = mockk()
    val roleRepository: RoleRepository = mockk()

    private val userMapper = UserMapper.INSTANCE

    val userService = UserService(userMapper, userRepository)

    @BeforeEach
    fun init() {
        ReflectionTestUtils.setField(userMapper, "roleService", RoleService(roleRepository))
    }

    @Test
    fun createBaseUser() {
        //given
        val user = TestUtils.createUser()
        val userDto = TestUtils.createUserDto()

        //when
        every { roleRepository.findByNameIn(any()) } returns listOf(
            Role(
                name = "ROLE_USER",
            )
        )
        every { userRepository.save(any()) } returns user

        //then
        val res = userService.createBaseUser(userDto)

        verify(exactly = 1) { userRepository.save(any()) }
        Assertions.assertNotNull(res)
    }

    @Test
    fun getUsers() {
        //given
        val user = TestUtils.createUser()
        val pageable = PageRequest.of(1, 10)
        val userPage = PageImpl(listOf(user), pageable, 1)

        //when
        every { userRepository.findAll(pageable) } returns userPage

        //then
        val pageRes = userService.getUsers(null, pageable)

        verify(exactly = 1) { userRepository.findAll(pageable) }
        Assertions.assertEquals(1, pageRes.page)
        Assertions.assertEquals(10, pageRes.pageSize)
        Assertions.assertTrue(pageRes.data?.isNotEmpty() ?: false)
    }

    @Test
    fun getUser() {
        //given
        val user = TestUtils.createUser()
        val userId = user.id ?: UUID.randomUUID()

        //when
        every { userRepository.findById(userId) } returns Optional.of(user)

        //then
        val userRes = userService.getUser(userId)

        verify(exactly = 1) { userRepository.findById(userId) }
        Assertions.assertNotNull(userRes)
        Assertions.assertEquals(user.name, userRes.name)
    }

    @Test
    fun deleteUser() {
        //given
        val user = TestUtils.createUser()
        val userId = user.id ?: UUID.randomUUID()

        //when
        every { userRepository.findById(userId) } returns Optional.of(user)
        every { userRepository.delete(any()) } returns Unit

        //then
        userService.deleteUser(userId)
        verify(exactly = 1) { userRepository.findById(userId) }
        verify(exactly = 1) { userRepository.delete(user) }
    }
}