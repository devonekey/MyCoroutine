package com.example.mycoroutine

import SectionParam
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mycoroutine.domain.logic.logic_1_1
import com.example.mycoroutine.ui.component.ChapterParam
import com.example.mycoroutine.ui.screen.ChaptersScreen
import com.example.mycoroutine.ui.screen.SectionsScreen
import com.example.mycoroutine.ui.theme.MyCoroutineTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

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
            val coroutineScope = rememberCoroutineScope()

            MyCoroutineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Init,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Init> {
                            ChaptersScreen(params = getChapters()) { index ->
                                navController.navigate(Chapter(index))
                            }
                        }
                        composable<Chapter> { backStackEntry ->
                            val chapterIndex = backStackEntry.toRoute<Chapter>().index

                            SectionsScreen(params = getSections(chapterIndex)) { sectionIndex ->
                                navController.navigate(Section(chapterIndex, sectionIndex))
                                coroutineScope.launch {
                                    when {
                                        chapterIndex == 0 && sectionIndex == 0 -> logic_1_1()
                                    }
                                }
                            }
                        }
                        composable<Section> { backStackEntry -> backStackEntry.toRoute<Section>() }
                    }
                }
            }
        }

        runBlocking {
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

    private fun asyncGetUserInfo(id: Int) = GlobalScope.async {
//        delay(1_100)

        user = UserInfo(id = id, name = "Susan", lastName = "Calvin")
    }

    private fun asyncIncrement(by: Int) = GlobalScope.async {
        for (i in 0 until by) {
            counter++
        }
    }

    private fun getChapters(): List<ChapterParam> {
        val titles = resources.getStringArray(R.array.chapter_titles)
        val descriptions = resources.getStringArray(R.array.chapter_descriptions)

        return titles.mapIndexed { index, s ->
            ChapterParam(
                title = s,
                description = descriptions[index]
            )
        }
    }

    private fun getSections(chapter: Int): List<SectionParam> {
        val (titles, descriptions) = when (chapter) {
            else -> resources.getStringArray(R.array.chapter1_section_titles) to
                    resources.getStringArray(R.array.chapter1_section_descriptions)
        }

        return titles.mapIndexed { index, s ->
            SectionParam(
                title = s,
                description = descriptions[index]
            )
        }
    }

    data class UserInfo(val name: String, val lastName: String, val id: Int)

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
}
