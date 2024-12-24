package com.example.mycoroutine.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.mycoroutine.ui.component.Chapter
import com.example.mycoroutine.ui.component.ChapterParam

@Composable
@Preview(showSystemUi = true)
fun ChaptersScreen(
    @PreviewParameter(ChapterScreenParamProvider::class) params: List<ChapterParam>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        params.forEachIndexed { index, param ->
            Chapter(
                param = ChapterParam(
                    title = param.title,
                    description = param.description
                ),
                onClick = { onClick(index) }
            )
        }
    }
}

class ChapterScreenParamProvider : PreviewParameterProvider<List<ChapterParam>> {
    override val values: Sequence<List<ChapterParam>>
        get() = sequenceOf(listOf(
            ChapterParam("Chapter 1", "Description"),
            ChapterParam("Chapter 2", "Description"),
            ChapterParam("Chapter 3", "Description")
        ))
}
