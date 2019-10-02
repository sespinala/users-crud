package com.userscrud.users_crud

import com.beust.klaxon.Klaxon
import com.userscrud.users_crud.endpoint.UserEndpoint
import io.vertx.core.Promise
import io.vertx.core.json.DecodeException
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.api.validation.ValidationException
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle

class MainVerticle : CoroutineVerticle() {

  private lateinit var userEndpoint: UserEndpoint

  override suspend fun start() {
    userEndpoint = UserEndpoint()

    val port = 8082
    val host = "0.0.0.0"
    val prefix = "/api"

    val router = initializeRouter(prefix)

    vertx.createHttpServer().requestHandler(router::accept).listen(port!!, host!!)
/*    vertx
      .createHttpServer()
      .requestHandler { req ->
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!")
      }
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause());
        }
      }*/
  }

  private fun initializeRouter(prefix: String): Router {
    val router = Router.router(vertx)

    router.route("$prefix/*").handler(BodyHandler.create())

    router.get("/health").handler(this::health)

    return router
  }

  private fun health(routingContext: RoutingContext) {
    routingContext.response().setStatusCode(200).end("Healthy")
  }
}
