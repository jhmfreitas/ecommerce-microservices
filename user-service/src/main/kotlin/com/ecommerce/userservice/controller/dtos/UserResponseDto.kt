package com.ecommerce.userservice.controller.dtos

data class UserResponseDto(
    val id: Long,
    val email: String,
    val name: String
)
