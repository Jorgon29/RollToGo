package com.terraplanistas.rolltogo.ui.screens.profile


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.navigations.LoginScreen

@Composable
fun ProfileScreen(
    vm: ProfielScreenViewmodel = viewModel(factory = ProfielScreenViewmodel.factory),
    nav: NavHostController,

    ) {
    var newName by remember { mutableStateOf("") }
    val loading by vm.loading.collectAsState()
    val userPicture = vm.userPicture.collectAsState()
    val username = vm.username.collectAsState()
    val userEmail = vm.userEmail.collectAsState()
    var editing by remember { mutableStateOf(false) }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
               vm.onImagePicked(it)
            }
        }
    )

    Log.d("imagen", "URI de imagen: ${userPicture.value}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(72, 94, 146))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .border(4.dp, Color(212, 175, 55), RoundedCornerShape(24.dp))
                        .background(Color(54, 70, 115), RoundedCornerShape(24.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(userPicture.value ?: Uri.EMPTY)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)),
                        onError = { error ->
                            Log.e("ProfileScreen", "Error al cargar la imagen: ${error.result}")

                        }
                    )
                }

                BasicTitle(username.value ?: "Sin Asignar")
                Text(
                    "Correo: ${userEmail.value ?: "Sin correo"}",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )

                if (editing) {
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Nuevo nombre") },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(212, 175, 55),
                            unfocusedIndicatorColor = Color.Gray,
                        )
                    )

                    Button(onClick = {
                        if (newName.isNotBlank()) vm.updateDisplayName(newName)
                    }) {
                        Text("Actualizar nombre")
                    }

                    Button(onClick = {
                        imageLauncher.launch("image/*")
                    }) {
                        Text("Cambiar foto de perfil")
                    }
                }

                Button(
                    onClick = { editing = !editing },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(212, 175, 55))
                ) {
                    Text(if (editing) "Cancelar" else "Modificar perfil")
                }

                if(!editing) {
                    Button(
                        onClick = {
                            vm.logout()
                            nav.navigate(LoginScreen){
                                popUpTo(LoginScreen) { inclusive = true } // Limpia la pila de navegación para evitar volver a la pantalla de perfil
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.7f))
                    ) {
                        Text("Cerrar sesión")
                    }
                }
            }
        }
    }
}



