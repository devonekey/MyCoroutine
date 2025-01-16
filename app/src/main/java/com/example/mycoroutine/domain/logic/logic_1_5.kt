package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private val TAG: String = "Logic_1_5"

fun logic_1_5() = runBlocking {
    val time = measureTimeMillis {
        val name = getName()
        val lastName = getLastName()

        Log.d(TAG, "Hello, $name $lastName")
    }
    val time2 = measureTimeMillis {
        val name = async { getName() }
        val lastName = async { getLastName() }

        Log.d(TAG, "Hello, ${name.await()} ${lastName.await()}")
    }

    Log.d(TAG, "Execution took $time ms")
    Log.d(TAG, "Execution took $time2 ms")
}

suspend fun getName(): String {
    delay(1_000)

    return "Susan"
}

suspend fun getLastName(): String {
    delay(1_000)

    return "Calvin"
}
