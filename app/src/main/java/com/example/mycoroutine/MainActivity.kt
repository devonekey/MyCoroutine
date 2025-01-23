package com.example.mycoroutine

import SectionParam
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mycoroutine.domain.logic.chapter3.section1.main as logic_3_1
import com.example.mycoroutine.domain.logic.chapter3.section2.main as logic_3_2
import com.example.mycoroutine.domain.logic.chapter3.section3.main as logic_3_3
import com.example.mycoroutine.domain.logic.chapter3.section4.main as logic_3_4
import com.example.mycoroutine.domain.logic.chapter3.section5.main as logic_3_5
import com.example.mycoroutine.domain.logic.logic_1_1
import com.example.mycoroutine.domain.logic.logic_1_2
import com.example.mycoroutine.domain.logic.logic_1_3
import com.example.mycoroutine.domain.logic.logic_1_4
import com.example.mycoroutine.domain.logic.logic_1_5
import com.example.mycoroutine.domain.logic.logic_2_1
import com.example.mycoroutine.domain.logic.logic_2_2
import com.example.mycoroutine.domain.logic.logic_2_3
import com.example.mycoroutine.domain.logic.logic_2_4
import com.example.mycoroutine.ui.component.ChapterParam
import com.example.mycoroutine.ui.screen.Chapter2Section4Screen
import com.example.mycoroutine.ui.screen.ChaptersScreen
import com.example.mycoroutine.ui.screen.SectionsScreen
import com.example.mycoroutine.ui.theme.MyCoroutineTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @Serializable
    object Init
    @Serializable
    data class Chapter(val index: Int)
    @Serializable
    data class Section(val chapterIndex: Int, val sectionIndex: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val count = mutableIntStateOf(0)

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
                                        chapterIndex == 0 && sectionIndex == 0 -> logic_1_1(this)
                                        chapterIndex == 0 && sectionIndex == 1 -> logic_1_2(this)
                                        chapterIndex == 0 && sectionIndex == 2 -> logic_1_3()
                                        chapterIndex == 0 && sectionIndex == 3 -> logic_1_4()
                                        chapterIndex == 0 && sectionIndex == 4 -> logic_1_5(this)
                                        chapterIndex == 1 && sectionIndex == 0 -> logic_2_1()
                                        chapterIndex == 1 && sectionIndex == 1 -> logic_2_2()
                                        chapterIndex == 1 && sectionIndex == 2 -> logic_2_3()
                                        chapterIndex == 1 && sectionIndex == 3 -> { count.intValue = logic_2_4().await() }
                                        chapterIndex == 2 && sectionIndex == 0 -> logic_3_1()
                                        chapterIndex == 2 && sectionIndex == 1 -> logic_3_2()
                                        chapterIndex == 2 && sectionIndex == 2 -> logic_3_3()
                                        chapterIndex == 2 && sectionIndex == 3 -> logic_3_4()
                                        chapterIndex == 2 && sectionIndex == 4 -> logic_3_5()
                                    }
                                }
                            }
                        }
                        composable<Section> { backStackEntry ->
                            val (chapterIndex, sectionIndex) = backStackEntry.toRoute<Section>()

                            when {
                                chapterIndex == 1 && sectionIndex == 3 -> Chapter2Section4Screen(count)
                            }
                        }
                    }
                }
            }
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
            1 -> resources.getStringArray(R.array.chapter2_section_titles) to
                    resources.getStringArray(R.array.chapter2_section_descriptions)
            2 -> resources.getStringArray(R.array.chapter3_section_titles) to
                    resources.getStringArray(R.array.chapter3_section_descriptions)
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
}
