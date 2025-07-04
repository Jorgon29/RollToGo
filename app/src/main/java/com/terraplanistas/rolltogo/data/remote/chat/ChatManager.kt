package com.terraplanistas.rolltogo.data.remote.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.gson.Gson
import com.terraplanistas.rolltogo.data.remote.responses.ChatMessageResponse
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

class ChatManager {

    private var stompClient: StompClient? = null
    private var topicSubscription: Disposable? = null
    internal var isConnected = false

    val messages = mutableStateListOf<ChatMessageResponse>()

    var onError: ((String) -> Unit)? = null

    fun connect(roomId: String, onConnected: () -> Unit = {}) {
        if (isConnected) {
            Log.d("STOMP", "Ya conectado, evitando reconexión.")
            return
        }

        stompClient = Stomp.over(
            Stomp.ConnectionProvider.OKHTTP,
            "ws://18.234.185.153:6942/ws"
        )

        stompClient?.lifecycle()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ lifecycleEvent ->
                when (lifecycleEvent.type) {
                    LifecycleEvent.Type.OPENED -> {
                        isConnected = true
                        Log.d("STOMP", "✅ Conectado al WebSocket")
                        subscribe(roomId)
                        onConnected()
                    }
                    LifecycleEvent.Type.ERROR -> {
                        isConnected = false
                        val errorMsg = lifecycleEvent.exception?.message ?: "Error desconocido"
                        Log.e("STOMP", "❌ Error de conexión: $errorMsg")
                        onError?.invoke(errorMsg)
                    }
                    LifecycleEvent.Type.CLOSED -> {
                        isConnected = false
                        Log.d("STOMP", "🔌 Conexión cerrada")
                    }
                    else -> Unit
                }
            }, { error ->
                isConnected = false
                Log.e("STOMP", "❌ Error en lifecycle: ${error.message}")
                onError?.invoke(error.message ?: "Error desconocido")
            })

        stompClient?.connect()
    }

    private fun subscribe(roomId: String) {

        topicSubscription?.dispose()
        messages.clear()

        val topicPath = "/topic/room-chat/$roomId"

        topicSubscription = stompClient?.topic(topicPath)

            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ message ->
                try {
                    val chatMessage = Gson().fromJson(message.payload, ChatMessageResponse::class.java)
                    messages.add(chatMessage)
                    Log.d("STOMP", "📩 Mensaje recibido: ${chatMessage.content}")
                } catch (e: Exception) {
                    val errorMsg = "Error al parsear mensaje: ${e.message}"
                    Log.e("STOMP", errorMsg)
                    onError?.invoke(errorMsg)
                }
            }, { error ->
                val errorMsg = "Error en suscripción: ${error.message}"
                Log.e("STOMP", errorMsg)
                onError?.invoke(errorMsg)
            })

        Log.d("STOMP", "Suscrito a $topicPath")
    }

    @SuppressLint("CheckResult")
    fun sendMessage(roomId: String, sender: String, content: String) {
        if (!isConnected) {
            val errorMsg = "No estás conectado al chat"
            Log.w("STOMP", errorMsg)
            onError?.invoke(errorMsg)
            return
        }

        val message = ChatMessageRequest(roomId, sender, content)
        val json = Gson().toJson(message)

        stompClient?.send("/app/ws/$roomId", json)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Log.d("STOMP", "✅ Mensaje enviado")
            }, { error ->
                val errorMsg = "Error al enviar mensaje: ${error.message}"
                Log.e("STOMP", errorMsg)
                onError?.invoke(errorMsg)
            })
    }

    fun disconnect() {
        topicSubscription?.dispose()
        topicSubscription = null
        stompClient?.disconnect()
        stompClient = null
        isConnected = false
        Log.d("STOMP", "🔌 Desconectado")
    }

    data class ChatMessageRequest(
        val roomId: String,
        val sender: String,
        val content: String
    )

}