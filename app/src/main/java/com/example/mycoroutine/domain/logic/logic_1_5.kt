package com.example.mycoroutine.domain.logic

import android.util.Log
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

    Log.d(TAG, "Execution took $time ms")
}

suspend fun getName(): String {
    delay(1_000)

    return "Susan"
}

suspend fun getLastName(): String {
    delay(1_000)

    return "Calvin"
}
