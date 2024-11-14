package com.example.calabozoscompuertas.ui.screens.book.pags

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calabozoscompuertas.R
import kotlinx.coroutines.delay

/**
 * Componente que representa una página donde los valientes llegan al tesoro más valioso tras
 * atravesar la peligrosa cueva "latch". El texto se muestra progresivamente, mientras una imagen
 * de fondo ilustra la escena. El texto se revela con un retraso entre cada letra, creando un
 * efecto de escritura en tiempo real.
 *
 * @param fullText El texto completo que se debe mostrar progresivamente en la página.
 */
@Composable
fun Pag8() {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        Atravesando la cueva latch, llegaron al tesoro mas valioso que se ha visto nunca, 
        pero pronto se dieron cuenta el porque no debieron olvidar sus espadas en aquella taverna...
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto de lanzamiento para mostrar el texto progresivamente
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Toma las primeras letras
            delay(20) // Retardo entre cada letra (ajusta para más o menos velocidad)
        }
    }

    // Box que contiene la imagen de la cueva y el tesoro
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina9), // Imagen del tesoro
            contentDescription = "Imagen del tesoro", // Descripción de la imagen
            contentScale = ContentScale.FillBounds, // La imagen llena el espacio disponible sin perder su proporción
        )
    }

    // Box que contiene el texto progresivo
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.TopCenter, // El texto se alinea en la parte superior central
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Estilo de fuente semi-negrita
            fontFamily = FontFamily.Serif, // Tipo de fuente serif
            color = Color.White, // Color del texto (blanco)
            fontSize = 16.sp, // Tamaño de la fuente
            textAlign = TextAlign.Left, // Alineación del texto a la izquierda
        )
    }
}