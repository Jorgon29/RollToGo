package com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle

import android.R.attr.shadowColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

@Composable
fun BasicTitle(title: String, modifier: Modifier = Modifier){
    val background: Color = MaterialTheme.colorScheme.surfaceContainer
    val border: Color = MaterialTheme.colorScheme.surface
    Box(
        modifier = modifier
            .padding(24.dp)
            .size(width = if(title.length > 10) (title.length*12).dp else 180.dp, height = 60.dp) // Controls size of hex
            .drawWithCache {
                val path = Path().apply {
                    val w = size.width
                    val h = size.height

                    moveTo(w * 0.15f, 0f)
                    lineTo(w * 0.85f, 0f)
                    lineTo(0.9f*w, h * 0.5f)
                    lineTo(w * 0.85f, h)
                    lineTo(w * 0.15f, h)
                    lineTo(0.1f*w, h * 0.5f)
                    close()
                }
                val shadowColor = Color.Black.copy(alpha = 0.95f)
                val paint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    color = background.toArgb()
                    setShadowLayer(16f, 0f, 4f, shadowColor.toArgb())
                }
                val borderPaint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = 8f
                    color = border.toArgb()
                }

                onDrawBehind {
                    drawIntoCanvas { canvas ->
                        canvas.nativeCanvas.drawPath(path.asAndroidPath(), paint)
                        canvas.nativeCanvas.drawPath(path.asAndroidPath(), borderPaint)
                    }
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BasicTitlePreview(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BasicTitle(title = "hola")
    }

}