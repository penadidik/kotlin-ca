Here’s a Kotlin Spring Boot project setup that uses **Clean Architecture** with a **modular structure** and **MongoDB** for `User` and `ToDo` CRUD operations.

---

### 🧱 **Project Structure (Clean Architecture - Modular)**

```
.
├── user-service
│   ├── domain
│   │   └── User.kt, UserRepository.kt
│   ├── application
│   │   └── UserService.kt
│   ├── infrastructure
│   │   └── UserMongoRepository.kt
│   ├── api
│   │   └── UserController.kt
│   └── UserModule.kt
├── todo-service
│   ├── domain
│   │   └── Todo.kt, TodoRepository.kt
│   ├── application
│   │   └── TodoService.kt
│   ├── infrastructure
│   │   └── TodoMongoRepository.kt
│   ├── api
│   │   └── TodoController.kt
│   └── TodoModule.kt
├── config
│   └── MongoConfig.kt
└── Application.kt
```

---

### 🧑‍💻 **Example Code Snippets**

#### `domain/User.kt`

```kotlin
package user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id val id: String? = null,
    val name: String,
    val email: String
)
```

#### `domain/UserRepository.kt`

```kotlin
package user.domain

interface UserRepository {
    fun findAll(): List<User>
    fun findById(id: String): User?
    fun save(user: User): User
    fun deleteById(id: String)
}
```

#### `infrastructure/UserMongoRepository.kt`

```kotlin
package user.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository
import user.domain.User

interface UserMongoRepository : MongoRepository<User, String>
```

#### `application/UserService.kt`

```kotlin
package user.application

import org.springframework.stereotype.Service
import user.domain.User
import user.infrastructure.UserMongoRepository

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
```

#### `api/UserController.kt`

```kotlin
package user.api

import org.springframework.web.bind.annotation.*
import user.application.UserService
import user.domain.User

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun all() = userService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = userService.getById(id)

    @PostMapping
    fun create(@RequestBody user: User) = userService.create(user)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody user: User) = userService.update(id, user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = userService.delete(id)
}
```

_(Structure for ToDo will mirror this. Replace `User` with `Todo`)_

---

### 🛠️ **MongoDB Config (MongoConfig.kt)**

```kotlin
package config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String = "clean_architecture_db"
}
```

---

### 🚀 **How to Run the Project**

1. **Make sure MongoDB is running locally** (or update URI in `application.properties`)
2. Add this in `application.properties`:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/clean_architecture_db
    server.port=8080
    ```
3. **Build & Run**:
    ```bash
    ./gradlew bootRun
    ```

---

### 🧪 Example Requests

```bash
# Create User
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name": "Alice", "email": "alice@example.com"}'

# Get Users
curl http://localhost:8080/users
```

---

Would you like me to generate a full downloadable project zip file with this setup, or you'd prefer just the full code directly here?
