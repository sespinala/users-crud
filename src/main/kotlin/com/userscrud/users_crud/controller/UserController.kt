package com.userscrud.users_crud.controller

import com.beust.klaxon.Klaxon
import com.userscrud.users_crud.domain.request.UserRequest
import com.userscrud.users_crud.service.UserService
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.api.RequestParameters

class UserController(userService: UserService? = null) {

  private val userService: UserService = userService ?: UserService()
  private val klaxon = Klaxon()

  suspend fun addUserHandler(routingContext: RoutingContext) {
    val parsedParams = routingContext.get<RequestParameters>("parsedParameters")
    val body = parsedParams.body().jsonObject
    val userRequest = body.mapTo(UserRequest::class.java)
    val user = userService.addUser(UserRequest.parseUserRequest(userRequest))
    routingContext.response().end(klaxon.toJsonString(user))
  }
}
