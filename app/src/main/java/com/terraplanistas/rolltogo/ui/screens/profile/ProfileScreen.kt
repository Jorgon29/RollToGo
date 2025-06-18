package com.terraplanistas.rolltogo.ui.screens.profile


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(
    vm: ProfielScreenViewmodel = viewModel(factory = ProfielScreenViewmodel.factory),
    nav: NavHostController
) {
    var newName by remember { mutableStateOf("") }
    val loading by vm.loading.collectAsState()
    val userPicture = vm.userPicture.collectAsState()
    val username = vm.username.collectAsState()
    val userEmail = vm.userEmail.collectAsState()






    if (!loading) {


        val imageLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? ->
                uri?.let {
                    vm.persistUri(it)?.let { persistUri ->
                        vm.onImagePicked(persistUri)
                    } ?: Log.e("ProfileScreen", "Error al persistir URI de imagen")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userPicture.value)
                        .crossfade(true)
                        .listener(
                            onError = { request, throwable ->
                                Log.e("Coil", "Error loading image, ${throwable.throwable.message.toString()}", )
                            },
                            onSuccess = { _, _ ->
                                Log.d("Coil", "Image loaded successfully")
                            }
                        )
                        .build(),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )




            Text("Nombre: ${username.value ?: "Sin nombre"}")
            Text("Correo: ${userEmail.value ?: "Sin correo"}")

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Nuevo nombre") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (newName.isNotBlank()) {
                    vm.updateDisplayName(newName)
                }
            }) {
                Text("Actualizar nombre")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                imageLauncher.launch("image/*")
            }) {
                Text("Subir nueva foto de perfil")
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

}


