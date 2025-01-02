package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private val TAG: String = "Logic_1_1"

fun logic_1_1() = runBlocking {
    Log.d(TAG, "${Thread.activeCount()} thread active at the start")

    val time = measureTimeMillis { createCoroutines(10_000) }

    Log.d(TAG, "${Thread.activeCount()} threads active at the end")
    Log.d(TAG, "Took $time ms")
}

private suspend fun createCoroutines(amount: Int) {
    val jobs = ArrayList<Job>()

    for (i in 1..amount) {
        jobs += GlobalScope.launch {
            Log.d(TAG, "Started $i in ${Thread.currentThread().name}")

            delay(1_000)

            Log.d(TAG, "Finished $i in ${Thread.currentThread().name}")
        }
    }

    jobs.forEach { it.join() }
}
