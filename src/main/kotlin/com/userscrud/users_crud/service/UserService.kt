package com.userscrud.users_crud.service

import com.userscrud.users_crud.domain.UserDomain
import com.userscrud.users_crud.dto.request.UserRequest
import com.userscrud.users_crud.model.User

class UserService {
  private val userDomain: UserDomain = UserDomain()

  suspend fun addUser(userRequest: UserRequest): User {
    return userDomain.addUser(userRequest)
  }
}
