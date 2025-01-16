package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val TAG: String = "Logic_1_2"
private lateinit var coroutineScope: CoroutineScope
lateinit var user: UserInfo

fun logic_1_2(scope: CoroutineScope) {
    coroutineScope = scope

    scope.apply { coroutineScope = this }.launch {
        asyncGetUserInfo(1)
        delay(1_000)

        Log.d(TAG, "User ${user.id} is ${user.name}")
    }
}

private fun asyncGetUserInfo(id: Int) = coroutineScope.async {
    delay(1_100)

    user = UserInfo(id = id, name = "Susan", lastName = "Calvin")
}

data class UserInfo(val name: String, val lastName: String, val id: Int)

