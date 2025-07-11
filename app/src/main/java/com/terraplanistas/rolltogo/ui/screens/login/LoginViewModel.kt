package com.terraplanistas.rolltogo.ui.screens.login

import android.util.Log
import androidx.browser.trusted.Token
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance
import com.terraplanistas.rolltogo.data.remote.dtos.UserCreateRequest
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class LoginViewModel(
    private val auth: FirebaseAuth,
    private val preference: UserPreferencesRepository

) : ViewModel() {


    private val _loginStatus = MutableStateFlow(false)
    val loginStatus: StateFlow<Boolean> = _loginStatus.asStateFlow()
    private val _error =
        MutableStateFlow("") // Mensaje de error en caso de que haya un problema al iniciar sesión o crear una cuenta
    val error: StateFlow<String> =
        _error.asStateFlow() // Mensaje de error en caso de que haya un problema al iniciar sesión o crear una cuenta, para ser usado en la ui
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


    /* Viene desde las preferencias y es para verificar en
    caso de que autologin se haya marcado*/
    val isAutoLogin: StateFlow<Boolean> = preference.loginPreference.map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    // Viene desde las preferencias y es para verificar si se debe mantener la sesión iniciada
    val savedUsername: StateFlow<String> = preference.usernamePreference.map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    // Viene desde las preferencias y es para verificar si se debe mantener la sesión iniciada
    val savedPassword: StateFlow<String> = preference.passwordPreference.map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    // Cambia el estado de si se debe mantener la sesión iniciada
    fun changeSaveLoginStatus(status: Boolean) {
        viewModelScope.launch {
            preference.saveLoginPreference(status)
        }
    }

    // Cambia el username guardado
    fun changeUsername(username: String) {
        viewModelScope.launch {
            preference.saveUsernamePreference(username)
        }
    }

    // Cambia el password guardado
    fun changePassword(password: String) {
        viewModelScope.launch {
            preference.savePasswordPreference(password)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            preference.saveAuthTokenKeyPreference(token)
        }
    }

    fun saveUuid(uuid: String){
        viewModelScope.launch {
            preference.saveUserUuid(uuid)
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        _loading.value = true


        if (!(email.isEmpty() || password.isEmpty())) {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginStatus.value = true
                        val user: FirebaseUser? = auth.currentUser
                        user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                            if (tokenTask.isSuccessful) {
                                try {
                                    val idToken: String? = tokenTask.result?.token
                                    saveToken(idToken ?: "")
                                    saveUuid(user.uid)
                                    viewModelScope.launch {
                                        val response = RetrofitInstance.userService.createUser(
                                            UserCreateRequest(
                                                userImageUrl = null,
                                                username = email,
                                                email = email,
                                                id = user.uid
                                            )
                                        )
                                    }
                                } catch (e: Exception) {
                                    _loginStatus.value = false
                                    _error.value =
                                        "Error de red/API al procesar login: ${e.localizedMessage ?: "Error desconocido"}"
                                }
                                _loading.value = false


                            } else {
                                _error.value = "Error al obtener el token de usuario"
                            }
                        }
                    } else {
                        _loginStatus.value = false
                        _error.value = "Por favor revise los datos ingresados"
                        _loading.value = false
                    }

                }
        } else {
            _loginStatus.value = false
            _error.value = "Debe llenar ambos campos antes de poder iniciar sesión"
            _loading.value = false
        }
    }


    /** * Registra un nuevo usuario con el correo y la contraseña proporcionados.
     * Si el registro es exitoso, se cambia el estado de loginStatus a true.
     * Si hay un error, se cambia el estado de loginStatus a false y se muestra un mensaje de error.
     */
    fun register(
        email: String,
        password: String
    ) {

        _loading.value = true
        if (!(email.isEmpty() || password.isEmpty())) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loading.value = false
                        _loginStatus.value = true
                        val user: FirebaseUser? = auth.currentUser
                        user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                            if (tokenTask.isSuccessful) {
                                try {
                                    val idToken: String? = tokenTask.result?.token
                                    saveToken(idToken ?: "")
                                    saveUuid(user.uid)
                                    viewModelScope.launch {
                                        val response = RetrofitInstance.userService.createUser(
                                            UserCreateRequest(
                                                userImageUrl = null,
                                                username = email,
                                                email = email,
                                                id = user.uid
                                            )
                                        )
                                    }
                                } catch (e: Exception) {
                                    _loginStatus.value = false
                                    _error.value =
                                        "Error de red/API al procesar login: ${e.localizedMessage ?: "Error desconocido"}"
                                }
                                _loading.value = false


                            } else {
                                _error.value = "Error al obtener el token de usuario"
                            }
                        }

                    } else {
                        _loginStatus.value = false
                        val exception = task.exception
                        _error.value =
                            if (exception != null && exception.message?.contains("email address is already in use") == true) {
                                "El correo ya está registrado"
                            } else {
                                "Hubo un error creando su usuario, por favor intente más tarde"
                            }
                        _loading.value = false
                    }
                }
        } else {
            _loginStatus.value = false
            _loading.value = false
            _error.value = "Debe llenar ambos campos antes de poder crear su usuario "
        }
    }

    /** *  Verifica si se ha puesto la preferencia de inicio de sesion automatico, en caso de serlo
     * intenta iniciar sesión automáticamente con el correo y la contraseña guardados.
     * Si no hay un usuario autenticado, cambia el estado de loginStatus a false.
     * Si el inicio de sesión automático es exitoso, cambia el estado de loginStatus a true.
     */
    fun autoLogin() {
        _loading.value = true
        Log.d("Error y loading", "Error: ${_error.value}, Loading: ${_loading.value}")
        Log.d(
            "AutoLogin",
            "Iniciando autologin con ${isAutoLogin.value}+${savedUsername.value} + ${savedPassword.value}"
        )
        viewModelScope.launch {
            if (isAutoLogin.value) {
                if (savedPassword.value.isNotEmpty() && savedUsername.value.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(savedUsername.value, savedPassword.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("AutoLogin", "Inicio de sesión automático exitoso")

                                _loginStatus.value = true
                                _loading.value = false
                                val user: FirebaseUser? = auth.currentUser
                                user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                                    if (tokenTask.isSuccessful) {
                                        try {
                                            val idToken: String? = tokenTask.result?.token
                                            saveToken(idToken ?: "")
                                            saveUuid(user.uid)
                                            viewModelScope.launch {
                                                val response = RetrofitInstance.userService.createUser(
                                                    UserCreateRequest(
                                                        userImageUrl = null,
                                                        username = savedUsername.value,
                                                        email = savedUsername.value,
                                                        id = user.uid
                                                    )
                                                )
                                            }
                                        } catch (e: Exception) {
                                            _loginStatus.value = false
                                            _error.value =
                                                "Error de red/API al procesar login: ${e.localizedMessage ?: "Error desconocido"}"
                                        }
                                        _loading.value = false


                                    } else {
                                        _error.value = "Error al obtener el token de usuario"
                                    }
                                }


                            } else if (task.exception != null) {
                                Log.d(
                                    "AutoLogin",
                                    "Error al iniciar sesión automáticamente: ${task.exception?.message}"
                                )
                                _loginStatus.value = false
                                _loading.value = false
                                _error.value =
                                    "Hubo un error al iniciar sesión automáticamente, por favor intente nuevamente"
                            }
                        }
                } else {
                    Log.d(
                        "AutoLogin",
                        "Estado de autologin: ${isAutoLogin.value}, Usuario: ${savedUsername.value}, Contraseña: ${savedPassword.value}"
                    )
                    _loading.value = false
                    _error.value =
                        "No se ha iniciado sesión automáticamente, por favor inicie sesión manualmente"

                }


            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                LoginViewModel(
                    auth = aplication.fireBaseAuth,
                    preference = aplication.appProvider.provideUserPreferenceRepository()
                )

            }

        }
    }
}