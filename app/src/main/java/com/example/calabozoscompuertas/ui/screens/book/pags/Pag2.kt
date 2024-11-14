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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calabozoscompuertas.R
import com.example.calabozoscompuertas.ui.components.Fire
import com.example.calabozoscompuertas.ui.theme.CalabozosCompuertasTheme
import kotlinx.coroutines.delay

/**
 * Pantalla del libro que muestra la cueva Wire, una de las subcuevas malditas de la cueva Lógica.
 * El texto se revela progresivamente y una imagen de la cueva se anima según el estado `active`.
 *
 * @param active Estado que controla la animación de la imagen de la cueva Wire. Si es `true`, la imagen es completamente visible.
 *               Si es `false`, la imagen se muestra con un nivel de opacidad reducido.
 *
 * Esta pantalla combina texto narrativo con una animación visual que responde a la interacción del usuario,
 * creando una experiencia inmersiva en el contexto del libro.
 */
@Composable
fun Pag2(active: Boolean) {
    // Texto completo que se va a mostrar progresivamente
    val fullText = """
        La cueva lógica tenía muchas subcuevas malditas, 
        la cueva Wire era la primera de ellas... justo en la entrada.
        La cueva wire no permitía que la luz fuera hecha a 
        menos que la antorcha fuera tomada por el portador
    """.trimIndent()

    // Estado para el texto que se muestra progresivamente
    var displayedText = remember { mutableStateOf("") }

    // Efecto que muestra el texto letra por letra con un pequeño retraso
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra el texto de manera progresiva
            delay(20) // Controla el tiempo entre cada letra
        }
    }

    // Animación para el nivel de opacidad de la imagen
    val alpha = animateFloatAsState(
        targetValue = if (active) 1f else 0.5f, // Si 'active' es verdadero, la imagen es completamente visible
        animationSpec = tween(durationMillis = 500),
        label = "" // Duración de la animación de la opacidad
    )

    // Box que contiene la imagen de la cueva Wire
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño de la pantalla
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.Center, // Alineación centrada
    ) {
        Image(
            painter = painterResource(id = R.drawable.pagina3), // Imagen de la cueva Wire
            contentDescription = "Imagen de la cueva Wire",
            alpha = alpha.value, // Controla la opacidad con la animación
            contentScale = Crop, // Escala la imagen recortándola para que ocupe el área completa
        )
    }

    // Box para mostrar el texto narrativo
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño de la pantalla
            .padding(40.dp), // Espaciado de 40dp alrededor del texto
        contentAlignment = Alignment.TopStart, // Alineación del texto al principio de la pantalla
    ) {
        Text(
            text = displayedText.value, // Muestra el texto progresivo
            fontWeight = FontWeight.SemiBold, // Peso de la fuente semi-negrita
            fontFamily = FontFamily.Serif, // Fuente con estilo serif
            color = Color.White, // Color blanco para el texto
            fontSize = 16.sp, // Tamaño de la fuente en 16sp
            textAlign = TextAlign.Left, // Alineación a la izquierda del texto
        )
    }

    // Componente Fire que se posiciona de manera personalizada sobre la pantalla
    Fire(
        active = active, // Controla la activación del componente Fire
        modifier = Modifier.layout { measurable, constraints ->
            // Posicionamiento personalizado usando layout
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, constraints.maxHeight) {
                // Se coloca en una posición específica dentro de la pantalla
                placeable.placeRelative(
                    x = (constraints.maxWidth * 0.28f).toInt(),
                    y = (constraints.maxHeight * -0.2f).toInt() // Movimiento hacia arriba para colocar más cerca de la parte superior
                )
            }
        }
    )
}

@Composable
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
private fun Preview() = CalabozosCompuertasTheme { Pag2(true) }