package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectionListCard(item: SelectionListItem) {
    val cardColor = Color(0xFF2E3A59)           // Deep blue card background
    val iconTint = Color(0xFFE4B23C)            // Gold
    val textColor = Color(0xFFE4B23C)           // Gold

    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(cardColor)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.4f)
            )
            .drawBehind {
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        center = center,
                        radius = size.maxDimension * 0.75f
                    ),
                    blendMode = BlendMode.Multiply
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon with circle background
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(cardColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                /* Icon(
                    painter = item.icon,
                    contentDescription = item.name,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                ) */
                Image(
                    item.icon,
                    item.name,
                    Modifier.size(24.dp),
                )
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                item.description?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
        }
    }
}
