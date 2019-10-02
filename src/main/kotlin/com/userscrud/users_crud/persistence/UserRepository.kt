package com.userscrud.users_crud.persistence

import com.mongodb.async.client.MongoCollection
import com.userscrud.users_crud.dto.request.UserRequest
import com.userscrud.users_crud.model.User
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.kotlin.coroutines.awaitResult
import org.litote.kmongo.async.KMongo
import org.litote.kmongo.async.getCollection

class UserRepository () {

  private val collection = initMongo()

  private fun initMongo(): MongoCollection<User> {
    val client = KMongo.createClient("mongodb://localhost:27017")
    val database = client.getDatabase("userscrud")
    return database.getCollection<User>("users")
  }

  private fun <T> mongoCallback(handler: Handler<AsyncResult<T>>) = {
      element: T?, throwable: Throwable? ->
        handler.handle(Future.succeededFuture(element))
  }

  suspend fun addUser(userRequest: UserRequest): User {
    val user = User(
      userRequest.id,
      userRequest.name,
      userRequest.username,
      userRequest.email
    )
    awaitResult<Void> { collection.insertOne(user, mongoCallback(it)) }
    return user
  }
}
