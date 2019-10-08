package com.userscrud.users_crud.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.userscrud.users_crud.model.User
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.litote.kmongo.KMongo
import com.mongodb.MongoClientURI
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object UserRepositoryTest: Spek({

  describe("When using a User repository") {
    lateinit var mongoExecutable: MongodExecutable
    lateinit var userRepository: UserRepository
    lateinit var collection: MongoCollection<User>
    lateinit var mongoDB: MongoDatabase

    beforeGroup {
      val mongodStarter = MongodStarter.getDefaultInstance()
      val port = 12345
      val mongodConfig = MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .net(
          Net(
            Network.getLocalHost().hostAddress,
            port,
            Network.localhostIsIPv6()
          )
        ).build()

      mongoExecutable = mongodStarter.prepare(mongodConfig)
      mongoExecutable.start()
      mongoDB = KMongo.createClient(
        MongoClientURI("mongodb://${Network.getLocalHost().hostAddress}:$port")
      ).getDatabase("userscrudtest")

      try {
        mongoDB.getCollection("users").drop()
      } catch (e: Exception) {
        println(e)
      }

      userRepository = UserRepository()
    }

    beforeEach {
      mongoDB.createCollection("users")
      collection = mongoDB.getCollection<User>("users")
      collection.insertOne(
        User(
          1,
          "name1",
          "username1",
          "name@email.com"
        )
      )
    }

    describe("Add an user") {
      it("Should insert the user successfully and return it") {
        val user =  User(
          2,
          "name2",
          "username2",
          "name2@email.com"
        )
        val userAdded = runBlocking { userRepository.addUser(user) }
        val expected = collection.findOne(User::username eq userAdded.username)

        assert(user == expected!!)
      }
    }

    describe("Update an user") {
      it("Should update the user successfully and return it") {
        val newUser = User(
          1,
          "newName",
          "username1",
          "newEmail@email.com"
        )
        val userIdUpdated = runBlocking { userRepository.updateUser(newUser) }
        val expected = collection.findOne(User::id eq userIdUpdated)

        assert(newUser == expected!!)
      }
    }

    describe("Get an user by username") {
      it("Should return the user successfully") {
        val user = User(
          1,
          "name1",
          "username1",
          "name@email.com"
        )
        val expected = runBlocking { userRepository.getUserByUsername(user.username) }

        assert(user == expected!!)
      }
    }

    describe("Delete an user") {
      it("Should remove the expected user") {
        val user = User(
          1,
          "name1",
          "username1",
          "name@email.com"
        )
        val expected = runBlocking { userRepository.deleteUser(user.username) }

        assert(user == expected!!)
      }
    }

    afterEach {
      try {
        mongoDB.getCollection("users").drop()
      } catch (e: Exception) {
        println(e)
      }
    }

    afterGroup {
      mongoExecutable.stop()
    }
  }
})
