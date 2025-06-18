package com.terraplanistas.rolltogo.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.ui.screens.login.LoginViewModel

class ProfielScreenViewmodel(
    private val auth: FirebaseAuth,
): ViewModel() {




    companion object{

        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                ProfielScreenViewmodel(
                    auth = aplication.fireBaseAuth,
                )

            }

        }
    }

}