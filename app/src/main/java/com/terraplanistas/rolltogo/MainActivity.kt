package com.terraplanistas.rolltogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.terraplanistas.rolltogo.ui.layout.CustomScaffold
import com.terraplanistas.rolltogo.ui.theme.RollToGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollToGoTheme {
                CustomScaffold()
            }
        }
    }
}

