package com.example.mycoroutine.domain.logic.chapter3.section2

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(3_000)

        println("Job1 is finished")
    }

    job1.start()

    println("Job1 is started")

    val job2 = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(3_000)

        println("Job2 is finished")
    }

    job2.join()

    println("Job2 is joined")
}
