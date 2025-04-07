package com.demo.kt_ca.user_service.domain

import com.demo.kt_ca.user_service.domain.User

interface UserRepository {
    fun findAll(): List<User>
    fun findById(id: String): User?
    fun save(user: User): User
    fun deleteById(id: String)
}
