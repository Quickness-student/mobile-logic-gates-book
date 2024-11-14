package com.example.calabozoscompuertas

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.calabozoscompuertas.ui.navcontroller.NavControllerStart
import com.example.calabozoscompuertas.ui.theme.CalabozosCompuertasTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Actividad principal de la aplicación que maneja la interfaz de usuario y la navegación.
 *
 * Esta actividad establece el tema de la aplicación, configura la pantalla de inicio (splash screen),
 * y gestiona la visibilidad de las barras del sistema mientras navega por las pantallas.
 *
 * @see NavControllerStart Para manejar la navegación en la pantalla principal.
 */
class MainActivity : ComponentActivity() {

    /**
     * Método que se llama cuando la actividad se crea.
     *
     * En este método, se configura la pantalla de inicio, se habilitan las opciones de pantalla completa
     * (Edge to Edge) y se establece la orientación de la pantalla a paisaje. Además, se inicializa la navegación
     * y se ocultan las barras del sistema.
     *
     * @param savedInstanceState Información sobre el estado previo de la actividad, si está disponible.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()  // Configura la pantalla de inicio (splash screen)
        super.onCreate(savedInstanceState)

        // Habilita la funcionalidad de Edge to Edge para utilizar toda la pantalla
        enableEdgeToEdge()

        // Establece la orientación de la pantalla a modo paisaje
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            // Aplica el tema de la aplicación
            CalabozosCompuertasTheme {
                val systemUiController = rememberSystemUiController()

                // Desactiva las barras del sistema (como la barra de estado y la barra de navegación)
                LaunchedEffect(Unit) {
                    systemUiController.isSystemBarsVisible = false
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent, // Hace que las barras del sistema sean transparentes
                        darkIcons = Color.White.luminance() > 0.5f // Determina si los iconos deben ser oscuros o claros
                    )
                }

                // Configura el controlador de navegación
                val navController = rememberNavController()

                // Utiliza Scaffold para administrar el diseño y la disposición de la UI
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    // Inicia la navegación desde el punto de partida de la aplicación
                    NavControllerStart(navController, padding)
                }
            }
        }
    }

    /**
     * Configura la pantalla de inicio (splash screen).
     *
     * Esta función usa la clase SplashScreen para personalizar la pantalla de inicio, en este caso,
     * configurando una condición para que desaparezca inmediatamente después de que la actividad
     * haya sido creada.
     */
    private fun setupSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { false } // La pantalla de inicio desaparece inmediatamente
    }
}
