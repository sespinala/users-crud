package com.userscrud.users_crud.repository

import com.mongodb.async.client.MongoCollection
import com.mongodb.client.result.DeleteResult
import com.userscrud.users_crud.model.User
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.kotlin.coroutines.awaitResult
import org.litote.kmongo.SetTo
import org.litote.kmongo.async.KMongo
import org.litote.kmongo.async.getCollection
import org.litote.kmongo.eq
import org.litote.kmongo.set

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

  suspend fun updateUser(user: User): User {
    val filter = User::id eq user.id
    val update = set(SetTo(User::javaClass, user))

    return awaitResult<User> { collection.findOneAndUpdate(filter, update, mongoCallback(it)) }
  }

  suspend fun getUserByUsername(user: User): User {
    return awaitResult<User> { collection.find(User::username eq user.username) }
  }

  suspend fun deleteUser(user: User) {
    awaitResult<DeleteResult> { collection.deleteOne(User::username eq user.username, mongoCallback(it)) }
  }
}
