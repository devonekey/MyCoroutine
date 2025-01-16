package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_2_1"

fun logic_2_1() = runBlocking {
    val task = GlobalScope.async { doSomething() }

    task.join()

    Log.d(TAG, "Complete")
}

fun doSomething() {
    throw UnsupportedOperationException("Can't do")
}
