package com.example.mycoroutine.domain.logic

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val TAG: String = "Logic_1_4"
lateinit var jobA: Job
lateinit var jobB: Job

fun logic_1_4() = runBlocking {
    jobA = launch {
        delay(1_000)

        jobB.join()
    }
    jobB = launch {
        jobA.join()
    }

    jobA.join()

    Log.d(TAG, "Finished")
}