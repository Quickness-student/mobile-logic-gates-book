package com.example.calabozoscompuertas.ui.screens.navcontroller.routes

sealed class RoutesStart(val route: String) {
    object Start : RoutesStart("Start")
}