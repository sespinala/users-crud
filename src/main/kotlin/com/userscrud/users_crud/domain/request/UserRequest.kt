package com.userscrud.users_crud.domain.request

import com.userscrud.users_crud.model.User

data class UserRequest (
  val id: Int = 0,
  val name: String = "no name",
  val username: String = "no username",
  val email: String? = null
) {
  companion object UserObject {
    fun parseUserRequest(userRequest: UserRequest): User = User(
      userRequest.id,
      userRequest.name,
      userRequest.username,
      userRequest.email
    )
  }
}
