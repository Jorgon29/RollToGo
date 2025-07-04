package com.terraplanistas.rolltogo.data.remote.chat.pruebaChat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.data.remote.chat.ChatManager
import com.terraplanistas.rolltogo.data.remote.responses.ChatMessageResponse
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

