package com.example.mycoroutine.ui.screen

import Section
import SectionParam
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(showSystemUi = true)
fun SectionsScreen(
    @PreviewParameter(SectionScreenParamProvider::class) params: List<SectionParam>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        params.forEachIndexed { index, param ->
            Section(
                param = SectionParam(
                    title = param.title,
                    description = param.description
                ),
                onClick = { onClick(index) }
            )
        }
    }
}

class SectionScreenParamProvider : PreviewParameterProvider<List<SectionParam>> {
    override val values: Sequence<List<SectionParam>>
        get() = sequenceOf(listOf(
            SectionParam("Section 1", "Description"),
            SectionParam("Section 2", "Description"),
            SectionParam("Section 3", "Description")
        ))
}
