package com.demo.kt_ca.user_service.api

import org.springframework.web.bind.annotation.*
import com.demo.kt_ca.user_service.application.UserService
import com.demo.kt_ca.user_service.domain.User

@RestContoller
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
