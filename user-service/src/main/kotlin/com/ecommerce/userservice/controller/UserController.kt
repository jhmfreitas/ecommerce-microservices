package com.ecommerce.userservice.controller

import com.ecommerce.userservice.controller.dtos.UserResponseDto
import com.ecommerce.userservice.model.User
import com.ecommerce.userservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/users")
final class UserController {

    private val userService: UserService

    constructor(userService: UserService) {
        this.userService = userService
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.getUsers())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(email: String, name: String, password: String): ResponseEntity<out Any?> {
        if (email.isBlank() || name.isBlank() || password.isBlank()) {
            return ResponseEntity.badRequest().build()
        }

        if (userService.getUserByEmail(email) != null) {
            val error: MutableMap<String?, String?> = HashMap()
            error.put("error", "User already exists with email: $email")
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error)
        }

        val user = userService.createUser(email, name, password)

        val createdUser = UserResponseDto(
            id = user.id ?: throw IllegalStateException("User ID should not be null"),
            email = user.email,
            name = user.name
        )

        val location: URI = URI.create("/users/" + createdUser.id)
        return ResponseEntity.created(location).body(createdUser)
    }
}