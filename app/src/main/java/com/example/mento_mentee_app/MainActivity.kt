package com.example.mento_mentee_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.mento_mentee_app.ui.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // ⬅️ Required for Hilt injection in Activities
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}
