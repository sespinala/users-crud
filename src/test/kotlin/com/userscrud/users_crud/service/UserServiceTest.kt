package com.userscrud.users_crud.service

import com.userscrud.users_crud.model.User
import com.userscrud.users_crud.repository.UserRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UserServiceTest: Spek ({

    describe("Add a user") {
      it("Should call successfully UserRepository") {
        val user = User(
          1,
          "name1",
          "username1",
          "name@email.com"
        )
        val userRepository = mockk<UserRepository>(relaxed = true)
        val userService = spyk(
          UserService(userRepository),
          recordPrivateCalls = true
        )

        runBlocking { userService.addUser(user) }
        // coVerify { userRepository.addUser(user) }
      }
    }

})
