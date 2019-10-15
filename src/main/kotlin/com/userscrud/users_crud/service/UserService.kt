package com.userscrud.users_crud.service

import com.userscrud.users_crud.handler.exceptions.ApiError
import com.userscrud.users_crud.handler.exceptions.ApiException
import com.userscrud.users_crud.model.User
import com.userscrud.users_crud.repository.UserRepository

class UserService(userRepository: UserRepository? = null) {
  private val userRepository = userRepository ?: UserRepository()

  suspend fun addUser(user: User): User {
    return if (userRepository.userExists(user.username)) {
      throw ApiException(ApiError.USER_ALREADY_EXISTS)
    } else {
      userRepository.addUser(user)
    }
  }

  suspend fun updateUser(user: User): Int {
    return if (userRepository.userExists(user.username)) {
      userRepository.updateUser(user)
    } else {
      throw ApiException(ApiError.USER_DOES_NOT_EXISTS)
    }
  }

  suspend fun getUserByUsername(username: String): User {
    return userRepository.getUserByUsername(username)
  }

  suspend fun deleteUser(username: String): User {
    return if (userRepository.userExists(username)) {
      return userRepository.deleteUser(username)
    } else {
      throw ApiException(ApiError.USER_DOES_NOT_EXISTS)
    }
  }

  suspend fun createFile(): String {
    return userRepository.createFile()
  }
}
