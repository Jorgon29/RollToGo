package com.terraplanistas.rolltogo.data.remote.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.platform.LocalLifecycleOwner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

class ChatManager {

    private var stompClient: StompClient? = null
    private var topicSubscription: Disposable? = null
    private var isConnected: Boolean = false

    /**
     * Establece la conexión y se suscribe al topic.
     * @param roomId ID de la sala de chat.
     * @param onConnected Callback cuando se conecte correctamente.
     */
    @SuppressLint("CheckResult")
    fun connect(roomId: String, onConnected: () -> Unit = {}) {
        if (isConnected) {
            Log.w("STOMP", "⚠️ Ya está conectado. Ignorando reconexión.")
            return
        }

        if (roomId.isBlank()) {
            Log.e("STOMP", "❌ roomId vacío. Cancelando conexión.")
            return
        }

        stompClient = Stomp.over(
            Stomp.ConnectionProvider.OKHTTP,
            "ws://18.234.185.153:6942/chat"
        )

        stompClient?.lifecycle()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ event ->
                when (event.type) {
                    LifecycleEvent.Type.OPENED -> {
                        isConnected = true
                        Log.d("STOMP", "✅ Conectado al WebSocket")
                        subscribe(roomId)
                        onConnected()
                    }
                    LifecycleEvent.Type.ERROR -> {
                        isConnected = false
                        Log.e("STOMP", "❌ Error de conexión: ${event.exception?.message}", event.exception)
                    }
                    LifecycleEvent.Type.CLOSED -> {
                        isConnected = false
                        Log.d("STOMP", "🔌 Conexión cerrada por el servidor")
                    }
                    else -> Unit
                }
            }, { error ->
                Log.e("STOMP", "❌ Error en lifecycle: ${error.message}", error)
            })

        stompClient?.connect()
        Log.d("STOMP", "🌐 Intentando conectar...")
    }

    /**
     * Suscripción al topic de una sala específica.
     */
    private fun subscribe(roomId: String) {
        topicSubscription?.dispose() // Cancelar suscripción previa si existe




        topicSubscription = stompClient?.topic("/room-chat/$roomId")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ message ->
                Log.d("STOMP", "📩 Mensaje recibido: ${message.payload}")
            }, { error ->
                Log.e("STOMP", "❌ Error en la suscripción: ${error.message}", error)
            })

        Log.d("STOMP", "📡 Suscrito a /room-chat/$roomId")
    }

    /**
     * Envía un mensaje al servidor.
     */
    @SuppressLint("CheckResult")
    fun sendMessage(roomId: String, sender: String, content: String) {
        if (!isConnected) {
            Log.w("STOMP", "⚠️ No conectado. Ignorando envío.")
            return
        }

        if (roomId.isBlank() || sender.isBlank() || content.isBlank()) {
            Log.e("STOMP", "❌ Parámetros inválidos. Verifica roomId, sender y content.")
            return
        }

        val json = """
            {
                "roomId": "$roomId",
                "sender": "$sender",
                "content": "$content"
            }
        """.trimIndent()

        stompClient?.send("/app/chat.sendMessage", json)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Log.d("STOMP", "✅ Mensaje enviado correctamente")
            }, { error ->
                Log.e("STOMP", "❌ Error al enviar mensaje: ${error.message}", error)
            })
    }

    /**
     * Desconecta el cliente y libera recursos.
     */
    fun disconnect() {
        topicSubscription?.dispose()
        topicSubscription = null

        stompClient?.disconnect()
        stompClient = null

        isConnected = false
        Log.d("STOMP", "🔌 Cliente desconectado y recursos liberados")
    }
}