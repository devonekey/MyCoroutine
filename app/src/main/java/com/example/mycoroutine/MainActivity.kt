package com.example.mycoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mycoroutine.ui.theme.MyCoroutineTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    lateinit var user: UserInfo
    var counter = 0
    lateinit var jobA: Job
    lateinit var jobB: Job
    @Serializable
    object Init
    @Serializable
    data class Chapter(val index: Int)
    @Serializable
    data class Section(val chapterIndex: Int, val sectionIndex: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCoroutineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                }
            }
        }

        runBlocking {
            Log.d(TAG, "${Thread.activeCount()} thread active at the start")

            val time = measureTimeMillis { createCoroutines(10_000) }

            Log.d(TAG, "${Thread.activeCount()} threads active at the end")
            Log.d(TAG, "Took $time ms")

            asyncGetUserInfo(1)
            delay(1_000)

            Log.d(TAG, "User ${user.id} is ${user.name}")

            val workerA = asyncIncrement(2_000)
            val workerB = asyncIncrement(100)

            workerA.await()
            workerB.await()

            Log.d(TAG, "counter: $counter")

            jobA = GlobalScope.launch {
                delay(1_000)

                jobB.join()
            }
            jobB = GlobalScope.launch {
                jobA.join()
            }

//            jobA.join()

            Log.d(TAG, "Finished")
        }
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

    private fun asyncGetUserInfo(id: Int) = GlobalScope.async {
//        delay(1_100)

        user = UserInfo(id = id, name = "Susan", lastName = "Calvin")
    }

    private fun asyncIncrement(by: Int) = GlobalScope.async {
        for (i in 0 until by) {
            counter++
        }
    }

    data class UserInfo(val name: String, val lastName: String, val id: Int)

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
}
