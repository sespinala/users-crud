package com.userscrud.users_crud.router

import com.userscrud.users_crud.controller.UserController
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.vertx.ext.web.api.validation.ParameterType

class UserRouter (
  private val controller: UserController,
  router: Router,
  prefix: String,
  schemas: JsonObject
) {

  private fun Route.coroutineHandler(function: suspend (RoutingContext)->Unit): Route = handler {
      routingContext->
        GlobalScope.launch(routingContext.vertx().dispatcher()) {
          try {
            function(routingContext)
          } catch (e: Exception) {
            routingContext.fail(e)
          }
        }
  }

  init {
    val userSchema = schemas.getJsonObject("userSchema")

    val validationHandlerUser = HTTPRequestValidationHandler.create()
      .addJsonBodySchema(userSchema.toString())

    val validationHandlerUserPathParam = HTTPRequestValidationHandler.create()
      .addPathParam("username", ParameterType.GENERIC_STRING)

    val validationHandlerCreateFile = HTTPRequestValidationHandler.create()

    router.post("$prefix/users")
      .handler(validationHandlerUser)
      .coroutineHandler { controller.addUserHandler(it) }

    router.put("$prefix/users")
      .handler(validationHandlerUser)
      .coroutineHandler { controller.updateUser(it) }

    router.get("$prefix/users/:username")
      .handler(validationHandlerUserPathParam)
      .coroutineHandler { controller.getUserByUsername(it) }

    router.delete("$prefix/users/:username")
      .handler(validationHandlerUserPathParam)
      .coroutineHandler { controller.deleteUser(it) }

    router.get("$prefix/create")
      .handler(validationHandlerCreateFile)
      .coroutineHandler { controller.createFile(it) }
  }
}
