package com.terraplanistas.rolltogo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.terraplanistas.rolltogo.ui.navigations.NavigationHost
import com.terraplanistas.rolltogo.ui.theme.RollToGoTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollToGoTheme {
                NavigationHost()
            }
        }
    }
}

