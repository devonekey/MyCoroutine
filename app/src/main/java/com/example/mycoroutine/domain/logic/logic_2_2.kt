package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_2_2"

fun logic_2_2() = runBlocking {
    val task = GlobalScope.launch {
        doSomething()
    }

    task.join()

    Log.d(TAG, "Complete")
}

private fun doSomething() {
    throw UnsupportedOperationException("Can't do")
}

