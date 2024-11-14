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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calabozoscompuertas.R
import kotlinx.coroutines.delay

/**
 * Pantalla del libro que muestra la cueva "not", una de las subcuevas malditas. El texto se revela progresivamente
 * y una imagen relacionada con la cueva cambia de opacidad en función del estado `active`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva "not". Si es `true`, la imagen se vuelve
 *               completamente transparente, si es `false`, la imagen es completamente visible.
 *
 * El componente genera una transición suave para la imagen y el texto, creando una experiencia visual inmersiva.
 */
@Composable
fun Pag3(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La siguiente subcueva maldita era la cueva not, 
        donde el ojo led brillaba con una fuerza cegadora 
        y solo podia ser apagado si el portador de la antorcha sostenia con firmeza su antorcha
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
        targetValue = if (active) 0f else 1f, // Si 'active' es true, la imagen se vuelve completamente transparente
        animationSpec = tween(durationMillis = 500),
        label = "" // Duración de la animación en milisegundos
    )

    // Box que contiene la imagen de la cueva "not"
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.TopCenter, // Alineación de la imagen en la parte superior central
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina4), // Imagen de la cueva "not"
            contentDescription = "Imagen de la cueva not", // Descripción de la imagen
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
}