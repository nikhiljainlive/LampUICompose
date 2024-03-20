package com.nikhiljain.lampuicompose.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview

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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LightShape_Preview() {
    LightShape()
}

