package com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.terraplanistas.rolltogo.R

@Composable
fun PlusButton(modifier: Modifier = Modifier, onClick: () -> Unit, size: Dp){
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        modifier = modifier
            .size(size)
            .offset(y = 42.dp)
            .border(width = 6.dp, color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
    ) {
        Icon(imageVector = Lucide.Plus, contentDescription = stringResource(R.string.plus_floating_button))
    }
}