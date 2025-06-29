package com.terraplanistas.rolltogo.ui.screens.content

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.composables.icons.lucide.Flame
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Scroll
import com.composables.icons.lucide.Sparkles
import com.composables.icons.lucide.Swords
import com.composables.icons.lucide.User
import com.terraplanistas.rolltogo.ui.navigations.ContentCreation

@Composable
fun ContentTypeSelectionScreen(
    nav: NavHostController,
) {
    val contentTypes = listOf(
        "Item" to Lucide.Swords,
        "Background" to Lucide.Scroll,
        "Creatures" to Lucide.Flame,
        "Species" to Lucide.User,
        "Spells" to Lucide.Sparkles
    )

    val backgroundColor = Color(72, 94, 146) // blue-500 de tu paleta
    val cardColors = listOf(
        Color(66, 86, 133),  // blue-600
        Color(51, 67, 104),  // blue-700
        Color(40, 52, 80),   // blue-800
        Color(30, 39, 61),   // blue-900
        Color(66, 86, 133)   // blue-600
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            "¿Qué quieres crear hoy?",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color(255, 255, 255),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contentTypes) { (type, icon) ->
                Card(
                    onClick = {
                               nav.navigate(ContentCreation(type = type))
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColors[contentTypes.indexOf(Pair(type, icon)) % cardColors.size]
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0x80000000)
                                    ),
                                    startY = 100f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = type,
                                modifier = Modifier.size(48.dp),
                                tint = Color(237, 239, 244) // blue-50 para los iconos
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = type,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    color = Color(255, 255, 255),
                                    fontWeight = FontWeight.Bold,
                                    shadow = Shadow(
                                        color = Color(0, 0, 0),
                                        offset = Offset(1f, 1f),
                                        blurRadius = 4f
                                    )
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

