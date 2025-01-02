package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_1_3"
var counter = 0

fun logic_1_3() = runBlocking {
    val workerA = asyncIncrement(2_000)
    val workerB = asyncIncrement(100)

    workerA.await()
    workerB.await()

    Log.d(TAG, "counter: $counter")
}

private fun asyncIncrement(by: Int) = GlobalScope.async {
    for (i in 0 until by) {
        counter++
    }
}
