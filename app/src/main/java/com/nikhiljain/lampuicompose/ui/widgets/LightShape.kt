package com.nikhiljain.lampuicompose.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LightShape(
    modifier: Modifier = Modifier,
    lightColor: Color = Color.White,
) {
    Canvas(
        modifier = modifier
            .aspectRatio(0.5f)
    ) {
        val path = Path().apply {
            moveTo(size.width * 0.25f, 0f)
            lineTo(x = size.width * 0.75f, y = 0f)
            lineTo(x = size.width * 1.25f, y = size.height)
            lineTo(x = size.width - (size.width * 1.25f), y = size.height)
        }
        drawPath(
            path,
            Brush.verticalGradient(
                colors = listOf(
                    lightColor, Color.Transparent, Color.Transparent
                )
            )
        )
    }
}

@Composable
fun LightShape2(
    lightColor: Color = Color.White,
) {
    Box(
        Modifier
            .width(300.dp)
            .aspectRatio(0.5f)
            .offset(0.dp, 250.dp)
            .drawBehind {
                val path = Path().apply {
                    moveTo((size.width / 2) * 0.5f, 0f)
                    lineTo(x = size.width, y = 0f)
                    lineTo(x = size.width * 0.75f, y = 0f)
                    lineTo(x = size.width * 1.25f, y = size.height)
                    lineTo(x = size.width - (size.width * 1.25f), y = size.height)
                }
                drawPath(
                    path, Brush.linearGradient(
                        colors = listOf(
                            lightColor, Color.Transparent, Color.Transparent
                        ),
                        start = Offset(size.width, 0f),
                        end = Offset(size.width, size.height)
                    )
                )
            }) {

    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LightShape_Preview() {
//    Box(modifier = Modifier.fillMaxSize()) {
    LightShape()
//    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LightShape2_Preview() {
//    Box(modifier = Modifier.fillMaxSize()) {
    LightShape2()
//    }
}

