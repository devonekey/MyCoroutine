package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_2_1"

@OptIn(InternalCoroutinesApi::class)
fun logic_2_1() = runBlocking {
    val task = GlobalScope.async { doSomething() }

    task.join()

    if (task.isCancelled) {
        val exception = task.getCancellationException()

        Log.d(TAG, "Error with message: ${exception.cause}")
    } else {
        Log.d(TAG, "Complete")
    }
}

private fun doSomething() {
    throw UnsupportedOperationException("Can't do")
}
