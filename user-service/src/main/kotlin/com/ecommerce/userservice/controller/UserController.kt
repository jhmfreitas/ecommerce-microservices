package com.ecommerce.userservice.controller

import com.ecommerce.userservice.model.User
import com.ecommerce.userservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}