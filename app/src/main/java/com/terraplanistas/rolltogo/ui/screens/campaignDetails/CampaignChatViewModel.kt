package com.terraplanistas.rolltogo.ui.screens.campaignDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.remote.chat.ChatManager.ChatMessageRequest
import com.terraplanistas.rolltogo.data.remote.responses.ChatMessageResponse
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.ui.screens.campaignList.CampaignListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent

class CampaignChatViewModel(

    val auth: FirebaseAuth,
    val preference: UserPreferencesRepository


): ViewModel() {
    private var stompClient: StompClient? = null
    private var topicSubscription: Disposable? = null
    internal var isConnected = false

    private val _textState = MutableStateFlow("Texto inicial")

    val textState: StateFlow<String> = _textState

    val messages = mutableStateListOf<ChatMessageResponse>()

    var onError: ((String) -> Unit)? = null

    fun onMessageChange(newMessage: String) {
        _textState.value = newMessage
    }


    @SuppressLint("CheckResult")
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

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                CampaignChatViewModel(
                    auth = aplication.fireBaseAuth,
                    preference = aplication.appProvider.provideUserPreferenceRepository()
                )

            }

        }
    }

}