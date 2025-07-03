package com.terraplanistas.rolltogo.data.remote.chat.pruebaChat

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import com.terraplanistas.rolltogo.data.remote.chat.ChatManager

@Composable
fun ChatTestScreen() {
    val roomId = "3b557478-3880-401f-b31b-b23214f4f363"
    val sender = "E0uI9r5aCnR8Jhbmv1YaHuiBb0j1"
    val chatManager = remember { ChatManager() }

    var message by remember { mutableStateOf(TextFieldValue("")) }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> chatManager.connect(roomId)
                Lifecycle.Event.ON_STOP -> chatManager.disconnect()
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Prueba de Chat con STOMP", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Escribe un mensaje") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (message.text.isNotBlank()) {
                    chatManager.sendMessage(roomId, sender, message.text)
                    Log.d("ChatTest", "Mensaje enviado: ${message.text}")
                    message = TextFieldValue("")
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar")
        }
    }
}
