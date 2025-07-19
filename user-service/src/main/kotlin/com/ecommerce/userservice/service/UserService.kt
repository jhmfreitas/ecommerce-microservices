package com.ecommerce.userservice.service

import com.ecommerce.userservice.model.User
import com.ecommerce.userservice.repository.UserRepository
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    private val userRepository: UserRepository
    private val log = LoggerFactory.getLogger(UserService::class.java)

    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun getUsers(): MutableList<User> {
        return userRepository.findAll().toMutableList()
    }

    fun getUserById(id: Long): User? {
        log.info { "Getting User by id: $id" }
        return userRepository.findById(id).orElse(null)
    }

    fun getUserByEmail(email: String): User? {
        log.info { "Getting User by email: $email" }
        return userRepository.findByEmail(email).orElse(null)
    }

    fun createUser(email: String, name: String, password: String): User {
        log.info { "Creating User with email: $email" }
        val existingUser = userRepository.findByEmail(email)
        if (existingUser.isPresent) {
            log.warn { "User with email $email already exists." }
            throw IllegalArgumentException("User with email $email already exists.")
        }
        val user = User(
            email = email,
            name = name,
            hashedPassword = Base64.getEncoder()
                .encode(password.encodeToByteArray())
        )
        return userRepository.save(user)
    }


}