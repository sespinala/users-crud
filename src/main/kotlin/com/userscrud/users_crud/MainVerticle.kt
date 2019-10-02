package com.userscrud.users_crud

import com.userscrud.users_crud.controller.UserController
import com.userscrud.users_crud.router.UserRouter
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult

class MainVerticle : CoroutineVerticle() {

  private lateinit var userController: UserController
  private val schemas = JsonObject()

  override suspend fun start() {
    userController = UserController()

    val port = 8082
    val host = "0.0.0.0"
    val prefix = "/api"

    val schemaAddUser = awaitResult<Buffer> { vertx.fileSystem().readFile("schemas/AddUser.json", it) }
    schemas.put("addUserSchema", schemaAddUser.toJsonObject())

    val router = initializeRouter(prefix)

    vertx.createHttpServer().requestHandler(router::accept).listen(port!!, host!!)
  }

  private fun initializeRouter(prefix: String): Router {
    val router = Router.router(vertx)

    UserRouter(userController, router, prefix, schemas)

    router.route("$prefix/*").handler(BodyHandler.create())

    router.get("/health").handler(this::health)

    return router
  }

  private fun health(routingContext: RoutingContext) {
    routingContext.response().setStatusCode(200).end("Healthy")
  }
}
