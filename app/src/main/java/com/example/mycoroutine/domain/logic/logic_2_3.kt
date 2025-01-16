package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_2_3"

fun logic_2_3() = runBlocking {
    val dispatcher = newSingleThreadContext("ServiceCall")
    val task = GlobalScope.launch(dispatcher) {
        printCurrentThread()
    }

    task.join()
}

fun printCurrentThread() {
    Log.d(TAG, "Running in thread [${Thread.currentThread().name}]")
}
