package com.example.mycoroutine.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun Chapter2Section4Screen(count: State<Int>) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (progressBar, newsCount) = createRefs()

        CircularProgressIndicator(
            modifier = Modifier.constrainAs(progressBar) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent

                start.linkTo(anchor = parent.start)
                top.linkTo(anchor = parent.top)
                end.linkTo(anchor = parent.end)
                bottom.linkTo(anchor = parent.bottom)
            },
            color = Color.Black
        )
        Text(
            text = "Found ${count.value} News",
            modifier = Modifier.constrainAs(newsCount) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent

                start.linkTo(anchor = parent.start)
                top.linkTo(anchor = progressBar.bottom, margin = 20.dp)
                end.linkTo(anchor = parent.end)
            }
        )
    }
}