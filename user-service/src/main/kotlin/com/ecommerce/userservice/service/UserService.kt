package com.ecommerce.userservice.service

import com.ecommerce.userservice.model.User
import com.ecommerce.userservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {

    private val userRepository: UserRepository

    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun getUsers(): MutableList<User> {
        return userRepository.findAll().toMutableList()
    }


}