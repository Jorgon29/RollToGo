package com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.R

@Composable
fun CategoryBox(title: String, content: String? = null){
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .border(width = 2.dp, color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .widthIn(max = (LocalConfiguration. current. screenWidthDp*0.9).dp)
            .heightIn(max = 150.dp)


    ) {
        Row(
           horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(14.dp),
        ) {

            Image(
                painter = painterResource(R.drawable.placeholder), stringResource(R.string.placeholder_image),
                modifier = Modifier
                    .size(56.dp)
                    .border(2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = title
                )
                Spacer(modifier = Modifier.height(16.dp))
                content?.let {
                    Text(text = content)
                }

                }
            }

        }
    }