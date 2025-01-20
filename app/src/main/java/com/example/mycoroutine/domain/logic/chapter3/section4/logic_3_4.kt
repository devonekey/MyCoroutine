package com.example.mycoroutine.domain.logic.chapter3.section4

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler {
            _: CoroutineContext, throwable: Throwable ->
        println("Job cancelled due to ${throwable.message}")
    }
    GlobalScope.launch(exceptionHandler) {
        TODO("Not implemented yet!")
    }
}
