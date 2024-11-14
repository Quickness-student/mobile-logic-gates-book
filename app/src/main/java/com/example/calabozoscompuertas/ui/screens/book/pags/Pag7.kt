package com.example.calabozoscompuertas.ui.screens.book.pags

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calabozoscompuertas.R
import com.example.calabozoscompuertas.ui.components.Fire
import kotlinx.coroutines.delay

/**
 * Componente que representa la última cueva "latch", un escenario peligroso en el que los valientes
 * deben ser astutos con sus antorchas. El texto se muestra progresivamente, y la imagen de fondo se
 * ajusta en opacidad según el estado del parámetro `active`. Además, se coloca un efecto de fuego en la
 * pantalla en una posición definida.
 *
 * @param active Controla el estado de la imagen y los efectos visuales. Si es `true`, la imagen se
 *              muestra con opacidad completa, y el efecto de fuego se activa. Si es `false`, la imagen
 *              tiene opacidad reducida, y el fuego es atenuado.
 */
@Composable
fun Pag7(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La ultima cueva... la cueva latch. Una cueva peligrosa y tamprosa, 
        pues los valientes debian de ser astutos con sus antorchas. 
        Un valiente es el que debera sostener la antorcha 
        para que la luz sea creada pero el otro debera soltarla hasta el final de la cueva.
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto progresivamente, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `active`
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.3f, // Si 'active' es true, la imagen se vuelve completamente visible
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "latch"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina8), // Imagen de la cueva "latch"
            contentDescription = "Imagen de la cueva latch", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.BottomCenter, // El texto se alinea en la parte inferior central
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

    // Efecto de fuego animado en una posición relativa dentro de la pantalla
    Fire(
        active = active,
        modifier = Modifier.layout { measurable, constraints ->
            // Define la posición exacta de la llama en función de la imagen
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * -0.39f).toInt(), // Posición horizontal del fuego
                    y = (constraints.maxHeight * -0.17f).toInt() // Posición vertical del fuego
                )
            }
        }
    )
}