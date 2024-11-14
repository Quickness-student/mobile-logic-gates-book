package com.example.calabozoscompuertas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Componente composable que simula una llama con un gradiente radial de colores cálidos.
 *
 * Este componente es visible solo cuando el parámetro [active] es verdadero. Al estar activo,
 * dibuja un círculo con un gradiente radial que simula el aspecto de una llama.
 *
 * El componente tiene las siguientes características:
 * - Ocupa todo el espacio disponible mediante `fillMaxSize()`.
 * - El área central de la llama tiene un tamaño fijo de 100 dp.
 * - El fondo tiene un gradiente radial con varios tonos de rojo y naranja, simulando un efecto de fuego.
 * - Se aplica un padding de 40 dp alrededor del componente para dar margen al círculo.
 *
 * @param active Un valor booleano que determina si el fuego debe ser mostrado o no. Si es `false`,
 *               el componente no se dibuja.
 * @param modifier Un modificador opcional que permite personalizar la apariencia y el comportamiento del componente.
 *                 Por ejemplo, se puede usar para agregar márgenes, cambiar el tamaño, o alterar la alineación.
 */
@Composable
fun Fire(active: Boolean, modifier: Modifier) {
    // Si el valor de 'active' es verdadero, se muestra el fuego
    if (active) {
        Box(
            modifier = modifier
                .fillMaxSize() // Rellena toda la pantalla o el espacio disponible
                .padding(40.dp), // Agrega un margen de 40dp alrededor
            contentAlignment = Alignment.Center, // Centra el contenido en la pantalla
        ) {
            // Caja interna que simula el fuego
            Box(
                modifier = Modifier
                    .size(100.dp) // El tamaño de la llama es 100dp
                    .clip(CircleShape) // La forma es circular
                    .background( // Definir el fondo con un gradiente radial
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFF5722).copy(alpha = 0.2f), // Color base rojo con transparencia
                                Color(0xFFFF7043).copy(alpha = 0.3f), // Color anaranjado con mayor transparencia
                                Color(0xFFFFAB40).copy(alpha = 0.2f), // Otro tono de anaranjado
                                Color(0xFFFF5722).copy(alpha = 0.3f), // Color base de nuevo
                                Color(0xFFFF7043).copy(alpha = 0.2f), // Otro tono de anaranjado
                            ),
                            radius = 300f, // Radio de la propagación del gradiente
                            center = Offset(0f, 0f) // El centro del gradiente está en el origen
                        )
                    )
            )
        }
    }
}