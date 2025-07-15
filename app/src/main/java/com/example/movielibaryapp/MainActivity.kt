package com.example.movielibaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.movielibaryapp.ui.navigation.MovieNavigation
import com.example.movielibaryapp.ui.theme.MovieLibaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieLibaryAppTheme {
                MovieNavigation()
            }
        }
    }
}



