package com.example.firebaseauth.presentation.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.firebaseauth.presentation.navgraph.Navigation
import com.example.firebaseauth.ui.theme.FireBaseAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireBaseAuthTheme {
                Navigation()
            }
        }
    }
}