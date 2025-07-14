package com.ecommerce.userservice.repository

import com.ecommerce.userservice.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}