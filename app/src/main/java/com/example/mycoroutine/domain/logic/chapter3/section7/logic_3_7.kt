package com.example.mycoroutine.domain.logic.chapter3.section7

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val job = GlobalScope.launch {
            delay(2_000)
        }

        job.join()
        job.start()
        job.join()
    }

    println("Took $time ms")
}
