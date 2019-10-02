package com.userscrud.users_crud.dto.request

data class UserRequest (
  val id: Int = 0,
  val name: String = "no name",
  val username: String = "no username",
  val email: String? = null
)
