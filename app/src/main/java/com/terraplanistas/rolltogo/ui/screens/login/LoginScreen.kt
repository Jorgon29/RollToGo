package com.terraplanistas.rolltogo.ui.screens.login


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation

@Composable
fun LoginScreen(
    vm: LoginViewModel = viewModel(factory = LoginViewModel.factory),
    nav: NavHostController
) {
    var email: MutableState<String> = remember { mutableStateOf("") } // Email ingresado
    var password: MutableState<String> = remember { mutableStateOf("") } // Contraseña ingresada
    val error = vm.error.collectAsState() // Mensaja los errores
    val loginPref = vm.isAutoLogin.collectAsState() // Estado de si se debe mantener la sesión iniciada, viene desde datastore
    val autoLoginChecked = remember { mutableStateOf(loginPref) } // Estado del checkbox de mantener sesión iniciada
    var creating: MutableState<Boolean> = remember { mutableStateOf(false) } // Estado de si se está creando una cuenta o iniciando sesión
    val loginStatus = vm.loginStatus.collectAsState() // Estado de si el usuario ha iniciado sesión correctamente
    val loading = vm.loading.collectAsState() // Estado de si se está cargando la pantalla

    val usernamePref = vm.savedUsername.collectAsState()
    val passwordPref = vm.savedPassword.collectAsState()

    LaunchedEffect(loginPref.value, usernamePref.value, passwordPref.value) { //Logea cuando tiene los valores necesarios
        if (loginPref.value && usernamePref.value.isNotBlank() && passwordPref.value.isNotBlank()) {
            vm.autoLogin()
        }
    }
    LaunchedEffect(loginStatus.value) { //Efecto que se ejecuta caundo login status es verdadero, por lo tanto, se ha iniciado sesión correctamente
        if (loginStatus.value) {
            Log.d("LoginScreen", "Login successful, navigating to forum")
            nav.navigate(ForumNavigation){
                popUpTo(ForumNavigation) { inclusive = true } // Limpia la pila de navegación para evitar volver a la pantalla de inicio de sesión
            } // Navega a la pantalla del foro
        }
    }

    if (loading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(72, 94, 146))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(72, 94, 146)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = Color.Black.copy(alpha = 0.4f),
                        spotColor = Color.Black.copy(alpha = 0.4f)
                    )
                    .background(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BasicTitle("¡Bienvenido de vuelta RollToGo!")

                Spacer(modifier = Modifier.height(60.dp))

                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text("Ingrese su correo electrónico") },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth(0.85f),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(35.dp))

                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    placeholder = { Text("Ingrese su contraseña") },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth(0.85f),
                    visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (!creating.value) {
                        Checkbox(
                            checked = autoLoginChecked.value.value, // Estado del checkbox de mantener sesión iniciada
                            onCheckedChange = { check ->
                                autoLoginChecked.value = mutableStateOf(check) // Cambia el estado del checkbox
                            },
                            modifier = Modifier,
                        )
                        Text("¿Desea mantener sesión iniciada?", color = Color.White)
                    }
                }
                if (!error.value.isEmpty()) { // Si hay un error, se muestra el mensaje de error
                    Text(error.value, color = Color.Red)
                }

                Spacer(modifier = Modifier.height(45.dp))

                Button(
                    onClick = {
                        if (creating.value) {
                            vm.register(email.value, password.value)
                        } else {
                            /*Inicia sesion y guarda el inicio de sesion utilizado,
                            de cualquier forma si autoLoginCHeked resulta ser falso, no serán utilizadas*/
                            Log.d(
                                "LoginScreen",
                                "Login button clicked with email: ${email.value} and password: ${password.value} and autoLogin: ${autoLoginChecked.value}"
                            )
                            vm.login(email.value, password.value)
                            vm.changeSaveLoginStatus(autoLoginChecked.value.value)
                            vm.changeUsername(email.value)
                            vm.changePassword(password.value)
                            vm.login(email.value, password.value)

                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(if (creating.value) "Registrarse" else "Iniciar Sesión")
                }

                Spacer(modifier = Modifier.height(20.dp))

                TextButton(onClick = {
                    creating.value =
                        !creating.value // Cambia el estado de si se está creando una cuenta o iniciando sesión
                }) {
                    Text(
                        if (creating.value) "¿Ya tienes cuenta? ¡Inicia sesión!" else "¿No tienes cuenta? ¡Crea una!",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

