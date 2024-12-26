package com.example.mycoroutine.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
fun Chapter(
    @PreviewParameter(ChapterParamProvider::class) param: ChapterParam,
    onClick: () -> Unit = {},
    paddingTop: Dp = 16.dp,
    paddingBottom: Dp = 16.dp,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() }
            .padding(horizontal = 16.dp)
            .padding(
                start = 16.dp,
                top = paddingTop,
                end = 16.dp,
                bottom = paddingBottom
            )
    ) {
        val (titleRef, descriptionRef) = createRefs()

        Title(
            title = param.title,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Description(
            description = param.description,
            modifier = Modifier.constrainAs(descriptionRef) {
                top.linkTo(titleRef.bottom, margin = 4.dp)
                start.linkTo(titleRef.start)
            }
        )
    }
}

data class ChapterParam(
    val title: String,
    val description: String
)

class ChapterParamProvider : PreviewParameterProvider<ChapterParam> {
    override val values: Sequence<ChapterParam>
        get() = listOf(ChapterParam("Chapter", "Description")).asSequence()
}
