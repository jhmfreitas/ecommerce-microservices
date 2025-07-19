package com.ecommerce.userservice.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleUserExists(ex: Exception): ResponseEntity<MutableMap<String?, String?>?> {
        val error: MutableMap<String?, String?> = HashMap<String?, String?>()
        error.put("error", ex.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body<MutableMap<String?, String?>?>(error)
    }
}