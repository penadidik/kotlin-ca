package com.example.kt_ca.user_service.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository
import com.example.kt_ca.user_service.domain.User

interface UserMongoRepository : MongoRepository<User, String>
