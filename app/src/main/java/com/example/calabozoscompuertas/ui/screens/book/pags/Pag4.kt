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
 * Pantalla del libro que muestra la cueva "or", una de las subcuevas malditas. El texto se revela progresivamente
 * y una imagen relacionada con la cueva "or" cambia de opacidad dependiendo del estado `active`.
 * También se visualiza un efecto de fuego en la pantalla con posiciones dinámicas, que depende del parámetro `touch`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva "or". Si es `true`, la imagen se vuelve completamente visible,
 *               si es `false`, la imagen tiene opacidad reducida.
 * @param touch Controla la aparición de efectos de fuego adicionales. Si es igual a 2, se muestra un segundo efecto de fuego.
 *
 * El componente genera una experiencia visual dinámica con texto progresivo, animación de opacidad y efectos de fuego.
 */
@Composable
fun Pag4(active: Boolean, touch: Int) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La cueva or era simple. 
        Almenos uno o ambos valientes 
        debia sostener la antorcha 
        para que la luz fuera creada.
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se muestra
    var displayedText = remember { mutableStateOf("") }

    // Efecto para mostrar el texto de manera progresiva, letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto letra por letra
            delay(20) // Controla la velocidad del efecto de revelado
        }
    }

    // Animación que controla la opacidad de la imagen según el estado `active`
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.5f, // Si 'active' es true, la imagen se vuelve completamente visible, de lo contrario, se reduce su opacidad
        animationSpec = tween(durationMillis = 500), // Duración de la animación de opacidad en milisegundos
        label = "" // Etiqueta para la animación
    )

    // Box que contiene la imagen de la cueva "or"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina5), // Imagen de la cueva "or"
            contentDescription = "Imagen de la cueva or", // Descripción de la imagen
            alpha = alpha.value, // Se aplica la animación de opacidad
            contentScale = Crop, // La imagen se recorta para llenar el área disponible
        )
    }

    // Box que contiene el texto
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Se agrega un espaciado alrededor del texto
        contentAlignment = Alignment.BottomStart, // El texto se alinea en la parte inferior izquierda
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

    // Efecto de fuego animado en una posición dinámica
    Fire(
        active = active,
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (constraints.maxWidth * -0.02f).toInt(), // Posiciona el fuego en un lugar específico
                    y = (constraints.maxHeight * -0.3f).toInt()
                )
            }
        }
    )

    // Si el parámetro 'touch' es igual a 2, se agrega un segundo efecto de fuego en una posición diferente
    if (touch == 2) {
        Fire(
            active = active,
            modifier = Modifier.layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.placeRelative(
                        x = (constraints.maxWidth * 0.25f).toInt(), // Posición diferente para el segundo efecto de fuego
                        y = (constraints.maxHeight * -0.3f).toInt()
                    )
                }
            }
        )
    }
}