package com.userscrud.users_crud.domain

import com.userscrud.users_crud.dto.request.UserRequest
import com.userscrud.users_crud.model.User
import com.userscrud.users_crud.persistence.UserRepository

class UserDomain (userRepository: UserRepository? = null) {
  private val userRepository = userRepository ?: UserRepository()

  suspend fun addUser(userRequest: UserRequest): User {
    return userRepository.addUser(userRequest)
  }
}
