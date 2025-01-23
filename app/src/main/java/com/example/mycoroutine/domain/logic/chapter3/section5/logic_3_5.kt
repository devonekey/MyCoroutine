package com.example.mycoroutine.domain.logic.chapter3.section5

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferred = GlobalScope.async {
        getHeadlines()
    }

    deferred.await()

    val otherDeferred = CompletableDeferred<Unit>()
}

private fun getHeadlines() {
}
