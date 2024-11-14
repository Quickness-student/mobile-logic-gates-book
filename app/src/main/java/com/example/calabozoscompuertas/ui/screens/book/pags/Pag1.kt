package com.example.calabozoscompuertas.ui.screens.book.pags

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Pantalla de introducción del libro donde el texto se muestra de manera progresiva.
 *
 * Esta función crea una animación de texto, donde un fragmento de narrativa se muestra
 * letra por letra, simulando una escritura progresiva. El texto es una introducción a
 * la historia dentro del libro interactivo y proporciona un inicio inmersivo para el usuario.
 *
 * @Composable
 * Función que representa la pantalla inicial con la narrativa del libro.
 */
@Composable
fun Pag1() {
    // Texto completo que se quiere mostrar de manera progresiva
    val fullText = """
        Los rumores eran ciertos en el reino de Itsu. Una cueva más antigua que la tierra misma del hombre existe en las profundidades de la montaña llamada "Sistema", y guarda un tesoro más valioso que todo el oro de los reyes de la tierra. Este lugar es conocido como "Lógica".
        
        Sin embargo, abundan las leyendas sobre la cueva, pues se dice que en su interior reina una terrible oscuridad causada por una maldición inquebrantable. La luz solo puede ser invocada por los más sabios de los Nueve Reinos.
        
        A pesar de haber escuchado las horrendas historias de la cueva de Lógica, dos caballeros de Itsu, llenos de valentía, decidieron templar su espíritu y adentrarse en el gran peligro...
    """.trimIndent()

    // Estado que mantiene el texto actual a medida que se va mostrando
    var displayedText = remember { mutableStateOf("") }

    // Efecto de lanzamiento para mostrar el texto letra por letra
    LaunchedEffect(fullText) {
        fullText.forEachIndexed { index, _ ->
            displayedText.value = fullText.take(index + 1) // Muestra las primeras letras progresivamente
            delay(20) // Controla la velocidad entre cada letra (ajustable)
        }
    }

    // UI para mostrar el texto de manera progresiva en el centro de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize() // El contenedor ocupa todo el tamaño de la pantalla
            .padding(60.dp) // Espaciado de 60dp alrededor del texto
            .background(colorScheme.background), // Fondo con el color de fondo del esquema de colores
        contentAlignment = Alignment.Center, // Alineación centrada del contenido
    ) {
        Text(
            text = displayedText.value, // Texto que se va mostrando progresivamente
            fontWeight = FontWeight.Medium, // Peso de la fuente para un texto no tan grueso
            fontFamily = FontFamily.Serif, // Fuente con estilo serif
            color = colorScheme.tertiary, // Color terciario del esquema de colores
            fontSize = 16.sp, // Tamaño de fuente de 16sp
            textAlign = TextAlign.Left, // Alineación a la izquierda del texto
        )
    }
}