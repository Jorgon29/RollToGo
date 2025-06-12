package com.terraplanistas.rolltogo.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.terraplanistas.rolltogo.RollToGoApp

class LoginViewModel(


) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _user = mutableStateOf<FirebaseUser?>(auth.currentUser)
    val user: FirebaseUser? = _user.value
    private val _loginStatus = mutableStateOf<Boolean>(false)
    val loginStatus: Boolean = _loginStatus.value
    private val _error = mutableStateOf("")
    val error: String = _error.value

    fun login(
        email: String,
        password: String
    ) {

        if(!(email.isEmpty() || password.isEmpty())){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _user.value = auth.currentUser
                        _loginStatus.value = true
                    } else {
                        _loginStatus.value = false
                        _error.value = "Por favor revise los datos ingresados"
                    }

                }

        }else{
            _loginStatus.value = false
            _error.value = "Debe llenar ambos campos antes de poder iniciar sesión"

        }


    }

    fun createAcount(
        email: String,
        password: String
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = auth.currentUser
                    _loginStatus.value = true
                } else {
                    _loginStatus.value = false
                }

            }

    }

    fun logout() {
        auth.signOut()
        _user.value = null
        _loginStatus.value = false
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                LoginViewModel(


                )

            }

        }
    }
}