package com.terraplanistas.rolltogo.ui.screens.campaignDetails

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.data.remote.chat.ChatManager
import com.terraplanistas.rolltogo.data.remote.responses.ChatMessageResponse
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CampaignChatScreen(
    campaignChatViewModel: CampaignChatViewModel = viewModel(
        factory = CampaignChatViewModel.factory,

    ),
    roomId: String,
    title: String? = null,
) {

    Log.d("",roomId)

    val message by campaignChatViewModel.textState.collectAsState()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val senderId = campaignChatViewModel.auth.currentUser?.uid

    LaunchedEffect(roomId) {
        if (!campaignChatViewModel.isConnected) {

            campaignChatViewModel.connect(roomId) {
                campaignChatViewModel.isConnected = true
            }

        }
    }


    LaunchedEffect(campaignChatViewModel) {
        campaignChatViewModel.onError = { error ->
            errorMessage = error
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf<Color>(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.surfaceContainer
                    ),
                    radius = 1300f
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title?.let {
            BasicTitle(title)
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(campaignChatViewModel.messages.reversed()) { msg ->
                MessageBubble(
                    message = msg,
                    isCurrentUser = msg.senderId == senderId
                )
            }
        }

        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
        }

        OutlinedTextField(
            value = message,
            onValueChange = { campaignChatViewModel.onMessageChange(it) },
            label = { Text("Escribe un mensaje") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (campaignChatViewModel.isConnected && message.isNotBlank()) {
                        campaignChatViewModel.sendMessage(roomId = roomId, senderId.toString(), message)
                        campaignChatViewModel.onMessageChange("")
                    }
                }
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    campaignChatViewModel.sendMessage(roomId = roomId, senderId.toString(), message)
                    campaignChatViewModel.onMessageChange("")
                },
                enabled = campaignChatViewModel.isConnected && message.isNotBlank(),
                modifier = Modifier.weight(1f)
            ) {
                Text("Enviar")
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageBubble(message: ChatMessageResponse, isCurrentUser: Boolean) {
    val bubbleColor = if (isCurrentUser) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.surfaceVariant

    val textColor = if (isCurrentUser) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = if (isCurrentUser) Alignment.End else Alignment.Start
    ) {
        // Nombre del remitente (solo si no es el usuario actual)
        if (!isCurrentUser) {
            Text(
                text = message.sender,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }

        // Burbuja del mensaje
        Card(
            shape = RoundedCornerShape(
                topStart = if (isCurrentUser) 16.dp else 4.dp,
                topEnd = if (isCurrentUser) 4.dp else 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = bubbleColor,
                contentColor = textColor
            ),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = message.createdAt.toLocalTime().toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Extensión para parsear el timestamp (ajusta según tu formato)
@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalTime(): LocalTime {
    return try {
        // Ajusta este formateador según el formato de tu timestamp
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        OffsetDateTime.parse(this, formatter).toLocalTime()
    } catch (e: Exception) {
        LocalTime.now() // Fallback
    }
}