package com.userscrud.users_crud.service

import com.userscrud.users_crud.domain.request.UserRequest
import com.userscrud.users_crud.model.User
import com.userscrud.users_crud.repository.UserRepository

class UserService(userRepository: UserRepository? = null) {
  private val userRepository = userRepository ?: UserRepository()

  suspend fun addUser(user: User): User {
    return userRepository.addUser(user)
  }

  suspend fun updateUser(user: User): Int {
    return userRepository.updateUser(user)
  }

  suspend fun getUserByUsername(username: String): User {
    return userRepository.getUserByUsername(username)
  }

  suspend fun deleteUser(username: String): User {
    return userRepository.deleteUser(username)
  }

  suspend fun createFile(): String {
    return userRepository.createFile()
  }
}
