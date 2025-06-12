package com.terraplanistas.rolltogo.ui.screens.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle

@Composable
fun LoginScreen(
     //vm: LoginViewModel = viewModel(factory = LoginViewModel.factory)
) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    //val error: String = vm.error
    //val login: Boolean = vm.loginStatus



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(72, 94, 146))
                .padding(top = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BasicTitle("¡Bienvenido a RollToGo!")

            Spacer(modifier = Modifier.height(60.dp))


            TextField(
                value = email.value,
                onValueChange = {email.value = it},
                placeholder = { Text("Ingrese su correo electronico") },
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(35.dp))


            TextField(
                value = password.value,
                onValueChange = {password.value = it},
                placeholder = { Text("Ingrese su correo electronico") },
                shape = RoundedCornerShape(50)
            )
            Row(modifier = Modifier.width(250.dp ),
                verticalAlignment = Alignment.CenterVertically
                ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {},
                    modifier = Modifier,
                    )
                Text("¿Desea mantener sesión iniciada?", color = Color.White)
            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {/*vm.login(email.value,password.value)*/},
                ) {Text("Iiniciar Sesion")}




        }


    }




@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    LoginScreen()
}