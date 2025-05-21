package com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.BookOpenText
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.Users
import com.terraplanistas.rolltogo.R

@Composable
fun PlusButton(
    modifier: Modifier = Modifier,
    size: Dp,
    expanded: Boolean,
    hide: () -> Unit,
    navigateToNewCharacter: () -> Unit,
    navigateNewCampaign: () -> Unit,
    showDropDown: () -> Unit
){
    FloatingActionButton(
        onClick = {showDropDown()},
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        modifier = modifier
            .size(size)
            .offset(y = 64.dp)
            .border(width = 6.dp, color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
    ) {
        Icon(imageVector = Lucide.Plus, contentDescription = stringResource(R.string.plus_floating_button))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { hide() },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
        ) {
            DropdownMenuItem(
                text = {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
                        Text(stringResource(R.string.new_actor))
                    }
                },
                onClick = { navigateToNewCharacter() },
                leadingIcon = {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
                        Icon(imageVector = Lucide.Users, contentDescription = stringResource(R.string.new_actor))
                    }
                }
            )

            DropdownMenuItem(
                text = {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
                        Text(stringResource(R.string.new_campaign))
                    }
                },
                onClick = { navigateToNewCharacter() },
                leadingIcon = {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimary) {
                        Icon(imageVector = Lucide.BookOpenText, contentDescription = stringResource(R.string.new_campaign))
                    }
                }
            )
        }
    }
}