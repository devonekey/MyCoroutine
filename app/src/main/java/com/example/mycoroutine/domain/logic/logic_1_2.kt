package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_1_2"
lateinit var user: UserInfo

fun logic_1_2() = runBlocking {
    asyncGetUserInfo(1)
    delay(1_000)

    Log.d(TAG, "User ${user.id} is ${user.name}")
}

private fun asyncGetUserInfo(id: Int) = GlobalScope.async {
    delay(1_100)

    user = UserInfo(id = id, name = "Susan", lastName = "Calvin")
}

data class UserInfo(val name: String, val lastName: String, val id: Int)

