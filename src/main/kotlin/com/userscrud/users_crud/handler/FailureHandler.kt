package com.userscrud.users_crud.handler

import com.beust.klaxon.Klaxon
import com.userscrud.users_crud.handler.exceptions.ApiError
import com.userscrud.users_crud.handler.exceptions.ApiException
import io.vertx.core.json.DecodeException
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.api.validation.ValidationException

class FailureHandler {

    private val klaxon = Klaxon()
    fun handleFailure(routingContext: RoutingContext) {
        val exception = routingContext.failure()
        when (exception) {
            is ApiException -> response(routingContext, exception.statusCode, klaxon.toJsonString(exception.apiError))
            // is IllegalArgumentException -> response(routingContext, 400, klaxon.toJsonString(ApiError.BAD_SKILL))
            is DecodeException -> response(routingContext, 400, klaxon.toJsonString(ApiError.BAD_BODY))
            is ValidationException -> response(routingContext, 400, klaxon.toJsonString(ApiError.BAD_BODY))
            null -> routingContext.next()
            else -> response(routingContext, 500, "Oops something went wrong")
        }
    }

    private fun response(routingContext: RoutingContext, statusCode: Int, message: String) {
        routingContext.response().setStatusCode(statusCode).end(message)
    }

}
