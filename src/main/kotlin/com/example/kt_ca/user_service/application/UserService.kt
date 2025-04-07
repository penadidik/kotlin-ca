package com.demo.kt_ca.user_service.application

import org.springframework.stereotype.Service
import com.demo.kt_ca.user_service.domain.User
import com.demo.kt_ca.user_service.infrastructure.UserMongoRepository

@Service
class UserService(private val repository: UserMongoRepository) {

    fun getAll(): List<User> = repository.findAll()
    fun getById(id: String): User? = repository.findById(id).orElse(null)
    fun create(user: User): User = repository.save(user)
    fun update(id: String, user: User): User {
        return repository.save(user.copy(id = id))
    }
    fun delete(id: String) = repository.deleteById(id)
}
