package com.example.himageapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.himageapp.screen.MainScreen

enum class Screen {
    Main,
    Compressor,
    Converter,
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.name,
        modifier = Modifier) {
        composable(route = Screen.Main.name) {
            MainScreen(
                onCompressorClick = {
                    navController.navigate(Screen.Compressor.name)
                },
                onConverterClick = {
                    navController.navigate(Screen.Converter.name)
                }
            )
        }
        composable(route = Screen.Compressor.name) {
            // CompressorScreen()
        }
    }
}