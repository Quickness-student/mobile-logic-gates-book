package com.example.calabozoscompuertas.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente composable que muestra una pantalla de tutorial con zonas interactivas divididas
 * en tres partes: izquierda, central y derecha. Cada zona tiene un color distinto y es visualmente
 * indicada mediante un rectángulo de color. También muestra instrucciones en la zona central.
 *
 * El tutorial tiene las siguientes secciones:
 * - **Zona izquierda**: Indicada con un color semi-transparente.
 * - **Zona central**: Donde se encuentran las instrucciones del tutorial.
 * - **Zona derecha**: Indicada con un color semi-transparente.
 *
 * En la zona central se muestra:
 * - Un título "Zona Central".
 * - Instrucciones para interactuar con el tutorial, como encender y apagar llamas,
 *   las cuales están listadas con numeración.
 *
 * Además, se incluye un Canvas que dibuja las zonas de toque, cada una con su color correspondiente,
 * ayudando a identificar las áreas interactivas en la pantalla.
 *
 * @Composable
 */
@Composable
fun Tutorial() {
    // Obtiene las configuraciones actuales de la pantalla (ancho)
    val configuration = LocalConfiguration.current

    // Calcula el ancho de la pantalla en píxeles
    val screenWidthPx = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }

    // Define los puntos de finalización para las zonas (izquierda, centro, derecha)
    val leftZoneEnd = screenWidthPx * 0.2f
    val centerZoneEnd = screenWidthPx * 0.8f  // El centro ocupa el 60% de la pantalla
    val rightZoneEnd = screenWidthPx

    // Caja contenedora principal para los elementos del tutorial
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla disponible
    ) {
        // Sección izquierda: Texto "Anterior"
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart) // Alineación a la izquierda
                .padding(start = 20.dp) // Padding de 20dp a la izquierda
        ) {
            Text(
                text = "Anterior", // Texto de la zona izquierda
                fontWeight = FontWeight.Bold, // Negrita
                fontSize = 26.sp, // Tamaño de la fuente
                color = Color.White, // Color blanco para el texto
            )
        }

        // Sección derecha: Texto "Siguiente"
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd) // Alineación a la derecha
                .padding(end = 20.dp) // Padding de 20dp a la derecha
        ) {
            Text(
                text = "Siguiente", // Texto de la zona derecha
                fontWeight = FontWeight.Bold, // Negrita
                fontSize = 26.sp, // Tamaño de la fuente
                color = Color.White, // Color blanco para el texto
            )
        }

        // Instrucciones centrales en la zona del medio
        Column(
            modifier = Modifier
                .align(Alignment.Center) // Centra el contenido en la pantalla
                .padding(16.dp), // Padding de 16dp alrededor de la zona central
            horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal
        ) {
            Text(
                text = "Zona Central", // Título de la zona central
                fontWeight = FontWeight.Bold, // Negrita
                fontSize = 24.sp, // Tamaño de la fuente
                color = Color.White, // Color blanco
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre el título y las instrucciones

            // Instrucciones para interactuar con el tutorial
            Text(
                text = "1 toque: Enciende una llama", // Instrucción 1
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(4.dp)) // Espaciado entre las instrucciones

            Text(
                text = "2 toques: Enciende 2 llamas", // Instrucción 2
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(4.dp)) // Espaciado entre las instrucciones

            Text(
                text = "Doble clic: Apaga las llamas", // Instrucción 3
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
            )
        }

        // Dibuja las zonas de toque usando un Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Zona izquierda (color rojo)
            drawRect(
                color = Color(0xFFFFFFFF).copy(alpha = 0.4f), // Color con transparencia
                size = androidx.compose.ui.geometry.Size(leftZoneEnd, size.height) // Zona izquierda
            )

            // Zona central (color verde)
            drawRect(
                color = Color(0x65000000).copy(alpha = 0.4f), // Color con transparencia
                topLeft = androidx.compose.ui.geometry.Offset(leftZoneEnd, 0f), // Comienza después de la zona izquierda
                size = androidx.compose.ui.geometry.Size(centerZoneEnd - leftZoneEnd, size.height) // Zona central
            )

            // Zona derecha (color azul)
            drawRect(
                color = Color(0xFFFFFFFF).copy(alpha = 0.4f), // Color con transparencia
                topLeft = androidx.compose.ui.geometry.Offset(centerZoneEnd, 0f), // Comienza después de la zona central
                size = androidx.compose.ui.geometry.Size(rightZoneEnd - centerZoneEnd, size.height) // Zona derecha
            )
        }
    }
}