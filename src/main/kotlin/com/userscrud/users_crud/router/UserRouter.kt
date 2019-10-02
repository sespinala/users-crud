package com.userscrud.users_crud.router

import com.userscrud.users_crud.endpoint.UserEndpoint
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRouter (
  private val controller: UserEndpoint,
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
    val addUserSchema = schemas.getJsonObject("addUserSchema")

    val validationHandlerAddUser = HTTPRequestValidationHandler.create()
      .addJsonBodySchema(addUserSchema.toString())

    router.post("$prefix/users/add")
      .handler(validationHandlerAddUser)
      .coroutineHandler { controller.addUserHandler(it) }
  }
}
