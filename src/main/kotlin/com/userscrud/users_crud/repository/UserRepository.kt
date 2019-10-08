package com.userscrud.users_crud.repository

import io.vertx.core.json.JsonObject
import com.mongodb.async.client.MongoCollection
import com.mongodb.client.result.UpdateResult
import com.userscrud.users_crud.model.User
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.awaitResult
import org.litote.kmongo.async.KMongo
import org.litote.kmongo.async.findOne
import org.litote.kmongo.async.getCollection
import org.litote.kmongo.eq

class UserRepository () {

  private val collection = initMongo()

  private fun initMongo(): MongoCollection<User> {
    val defaultDB = generateDatabaseInfo(
      "mongodb://localhost:12345",
      "userscrudtest"
    )
    val localDB = generateDatabaseInfo(
      "mongodb://localhost:27017",
      "userscrud"
    )

    val config = if (Vertx.currentContext()?.config() != null) localDB else defaultDB
    val client = KMongo.createClient(config.getString("databaseURI"))
    val database = client.getDatabase(config.getString("databaseName"))
    return database.getCollection<User>("users")
  }

  private fun <T> mongoCallback(handler: Handler<AsyncResult<T>>) = {
      element: T?, throwable: Throwable? ->
        handler.handle(Future.succeededFuture(element))
  }

  private fun generateDatabaseInfo(databaseURI: String, databaseName: String): JsonObject {
    return JsonObject()
      .put("databaseURI", databaseURI)
      .put("databaseName", databaseName)
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
    return awaitResult { collection.findOne(User::username eq username, mongoCallback(it)) }
  }

  suspend fun deleteUser(username: String): User {
    return awaitResult { collection.findOneAndDelete(User::username eq username, mongoCallback(it)) }
  }
}
