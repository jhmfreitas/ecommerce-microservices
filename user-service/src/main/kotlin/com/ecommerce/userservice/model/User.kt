package com.ecommerce.userservice.model

import jakarta.persistence.*

@Entity
@Table(name = "T_USER")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val hashedPassword: ByteArray? = null
) {
    protected constructor() : this(null, "", "", null)
}