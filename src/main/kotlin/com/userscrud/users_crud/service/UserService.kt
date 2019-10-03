package com.userscrud.users_crud.service

import com.userscrud.users_crud.domain.request.UserRequest
import com.userscrud.users_crud.model.User
import com.userscrud.users_crud.repository.UserRepository

class UserService {
  private val userRepository = UserRepository()

  suspend fun addUser(user: User): User {
    return userRepository.addUser(user)
  }

  suspend fun updateUser(user: User): User {
    return userRepository.updateUser(user)
  }

  suspend fun getUserByUsername(user: User): User {
    return userRepository.getUserByUsername(user)
  }

  suspend fun deleteUser(user: User) {
    return userRepository.deleteUser(user)
  }
}
