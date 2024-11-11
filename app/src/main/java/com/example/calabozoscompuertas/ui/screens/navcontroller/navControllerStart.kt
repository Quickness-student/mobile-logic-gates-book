package com.example.calabozoscompuertas.ui.screens.navcontroller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calabozoscompuertas.ui.screens.navcontroller.routes.RoutesStart
import com.example.calabozoscompuertas.ui.screens.start.StartScreen

@Composable
fun NavControllerStart(rememberNavController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = rememberNavController,
        startDestination = RoutesStart.Start.route,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(padding),
    ) {
        composable(RoutesStart.Start.route) { StartScreen() }
    }
}