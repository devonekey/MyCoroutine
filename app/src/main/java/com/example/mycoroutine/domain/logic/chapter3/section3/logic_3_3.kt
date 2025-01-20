package com.example.mycoroutine.domain.logic.chapter3.section3

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(InternalCoroutinesApi::class)
fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(5_000)
    }

    delay(2_000)
    job.cancel(cause = CancellationException("Timeout!"))

    val cancellation = job.getCancellationException()

    print("message: ${cancellation.message}")
}
