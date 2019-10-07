package com.userscrud.users_crud.repository

import com.mongodb.async.client.MongoCollection
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.userscrud.users_crud.model.User
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.kotlin.coroutines.awaitResult
import org.litote.kmongo.async.KMongo
import org.litote.kmongo.async.findOne
import org.litote.kmongo.async.findOneAndDelete
import org.litote.kmongo.async.getCollection
import org.litote.kmongo.eq
import org.litote.kmongo.json

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

  suspend fun addUser(user: User): User {
    awaitResult<Void> { collection.insertOne(user, mongoCallback(it)) }
    return user
  }

  suspend fun updateUser(user: User): Int {
    val filter = User::username eq user.username
    return (awaitResult<UpdateResult> { collection.replaceOne(filter, user, mongoCallback(it)) }.upsertedId to user.id).second
  }

  suspend fun getUserByUsername(username: String): User {
    return awaitResult<User> { collection.findOne(User::username eq username, mongoCallback(it)) }
  }

  suspend fun deleteUser(username: String): User {
    return awaitResult<User> { collection.findOneAndDelete(User::username eq username, mongoCallback(it)) }
  }
}
