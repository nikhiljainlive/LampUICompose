package com.nikhiljain.lampuicompose.ui.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.nikhiljain.lampuicompose.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun FinalView(
    lightColor: Color = Color.White,
    handleColor: Color = Color.White
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp        // TODO :: resize widgets
    val coroutineScope = rememberCoroutineScope()
    val offsetY = remember { Animatable(0f) }
    var isDragging by remember { mutableStateOf(false) }
    var lightAlpha by remember { mutableFloatStateOf(0.1f) }
    var isLampOn by remember { mutableStateOf(false) }
    Box {
        Row {
            Column(modifier = Modifier.width(250.dp)) {
                Image(
                    modifier = Modifier
                        .width(200.dp)
                        .offset(x = 30.dp)
                        .zIndex(1f),
                    contentDescription = "Lamp",
                    painter = painterResource(id = R.drawable.lamp)
                )
                if (isLampOn) {
                    LightShape(
                        modifier = Modifier
                            .width(250.dp)
                            .offset(y = -(10.dp))
                            .alpha(lightAlpha),
                        lightColor = lightColor
                    )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            HandleView(
                modifier = Modifier
                    .offset { IntOffset(0, offsetY.value.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                coroutineScope.launch {
                                    change.consume()
                                    isDragging = true
                                    offsetY.snapTo(offsetY.value + dragAmount.y)
                                }
                            }, onDragEnd = {
                                coroutineScope.launch {
                                    isDragging = false
                                    if (offsetY.value > 200f) {
                                        isLampOn = !isLampOn
                                    }
                                    offsetY.animateTo(
                                        targetValue = 0f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    )
                                }
                            }
                        )
                    },
                handleColor = handleColor,
                handleHeight = 80.dp,
                handleRopeWidth = 3.dp
            )
        }
        if (isLampOn) {
            BottomSliderView(
                modifier = Modifier.offset(y = (screenHeight - 250.dp)),
                lightAlpha = lightAlpha
            ) { lightAlpha = it }
        }
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            LightStatusButton(
                backgroundColor = if (isLampOn) Color.Green else Color.Red,
                text = if (isLampOn) "O N" else "O F F"
            )
        }
    }
}

@Composable
private fun BottomSliderView(
    modifier: Modifier = Modifier,
    viewColor: Color = Color.White,
    lightAlpha: Float,
    onSliderValueChange: (Float) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = String.format("%.1f", lightAlpha),
            fontWeight = FontWeight.Medium,
            fontSize = 48.sp,
            color = viewColor
        )
        Slider(
            value = lightAlpha,
            steps = 8,
            valueRange = 0.1f..1f,
            colors = SliderDefaults.colors(
                thumbColor = viewColor,
                activeTrackColor = viewColor
            ),
            onValueChange = onSliderValueChange
        )
        Text(
            text = "O P A C I T Y",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = viewColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF252525)
@Composable
fun FinalView_Preview() {
    FinalView()
}

@Preview(showBackground = true, backgroundColor = 0xFF252525)
@Composable
fun BottomSliderView_Preview() {
    BottomSliderView(lightAlpha = 0.1f) {}
}

