package com.ecommerce.userservice.model

import com.ecommerce.userservice.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun createUserTest() {
        val email = "userEmail@gmail.com"
        val passwordInBytes = "hashedPassword".encodeToByteArray()
        val user = User(null, email, "User Name", passwordInBytes)
        userRepository.save(user)

        assertNotNull(user)
        assertNotNull(user.id)
        assertEquals(email, user.email)
        assertEquals("User Name", user.name)
        assertEquals(passwordInBytes, user.hashedPassword)
    }

    @Test
    fun getEmail() {
    }

    @Test
    fun getName() {
    }

    @Test
    fun getHashedPassword() {
    }

}