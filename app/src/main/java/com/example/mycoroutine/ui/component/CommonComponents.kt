package com.example.mycoroutine.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
fun Title(
    @PreviewParameter(TitleProvider::class) title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
fun Description(
    @PreviewParameter(DescriptionProvider::class) description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodySmall
    )
}

class TitleProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = listOf("Title").asSequence()
}

class DescriptionProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = listOf("Description").asSequence()
}
