package com.example.mycoroutine.domain.logic.chapter3.section1

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    GlobalScope.launch(start = CoroutineStart.LAZY) {
        TODO("Not implemented yet!")
    }

    delay(500)
}
