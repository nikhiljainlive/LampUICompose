package com.nikhiljain.lampuicompose.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset

@Composable
fun HandleView(
    modifier: Modifier = Modifier,
    handleColor: Color = Color.Black,
    handleHeight: Dp = 100.dp,
    handleRopeWidth : Dp = 2.5.dp,
    handleRopeHeight: Dp = 1000.dp
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.offset(
                            0, -700
                        )
                    )
                    val extraHeight = 1000
                    layout(
                        placeable.width,
                        placeable.height + extraHeight
                    ) { placeable.place(0, extraHeight) }
                },
            verticalArrangement = Arrangement.spacedBy(-(handleRopeHeight + (handleHeight / 2)))
        ) {
            Box(
                modifier = Modifier
                    .height(handleHeight)
                    .aspectRatio(0.5f)
                    .border(handleRopeWidth, color = handleColor, RoundedCornerShape(25.dp))
            )

            Box(
                modifier = Modifier
                    .width(handleRopeWidth)
                    .height(handleRopeHeight)
                    .align(Alignment.CenterHorizontally)
                    .background(handleColor)
            )

        }
        Text(
            modifier = Modifier
                .offset(-(40.dp), 250.dp)
                .rotate(-90f)
                .alpha(0.2f),
            text = "PULL DOWN",
            color = handleColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HandleView_Preview() {
    Box(modifier = Modifier.fillMaxSize()) {
        HandleView(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y = -(150.dp))
        )
    }
}