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
 * Componente que representa la cueva "xor", una de las etapas en la que los dos valientes
 * se enfrentan a un desafío donde no pueden sostener la antorcha al mismo tiempo.
 * El texto se muestra de forma progresiva, y se utiliza una imagen de fondo que se desvanece
 * según el estado del parámetro `value`. Además, se muestra un efecto visual de fuego en la pantalla.
 *
 * @param value Controla el estado de visibilidad de la imagen y los efectos visuales en la pantalla.
 *              Si es `true`, la imagen se muestra con opacidad completa, y el efecto de fuego está activo.
 *              Si es `false`, la imagen tiene opacidad reducida, y el efecto de fuego es atenuado.
 */
@Composable
fun Pag6(value: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        Cerca de su destino se encuentran la cueva xor.
        Los dos valientes 
        no podian sostener la antorcha al mismo tiempo
        para que la luz fuera creada
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

    // Animación que controla la opacidad de la imagen según el estado `value`
    val alpha = animateFloatAsState(
        targetValue = if (value) 1f else 0.5f, // Si 'value' es true, la imagen se vuelve completamente visible, de lo contrario, se reduce su opacidad
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "xor"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina7), // Imagen de la cueva "xor"
            contentDescription = "Imagen de la cueva xor", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.TopStart, // El texto se alinea en la parte superior izquierda
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
        active = value,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * 0.27f).toInt(), // Posición del fuego
                    y = (constraints.maxHeight * -0.3f).toInt() // Posición del fuego
                )
            }
        }
    )
}