package com.example.calabozoscompuertas.ui.screens.book.pags

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.calabozoscompuertas.R
import com.example.calabozoscompuertas.ui.components.Tutorial
import com.example.calabozoscompuertas.ui.theme.CalabozosCompuertasTheme

/**
 * Componente que representa una pantalla de tutorial con una imagen de fondo y un componente
 * de tutorial superpuesto. La imagen de fondo llena toda la pantalla y la imagen se ajusta
 * con el escalado `ContentScale.Crop`, mientras que el componente `Tutorial` se muestra encima.
 *
 * @see Tutorial El componente que muestra el contenido de tutorial en la pantalla.
 */
@Composable
fun TutorialScreen() {
    // Caja que contiene la imagen de fondo y el componente de tutorial
    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el tamaño de la pantalla
            .background(colorScheme.background) // Fondo con el color del esquema de colores
    ) {
        // Imagen que cubre toda la pantalla y se ajusta con ContentScale.Crop
        Image(
            painter = painterResource(id = R.drawable.pagina3), // Imagen de fondo (tutorial)
            contentDescription = "Tutorial", // Descripción de la imagen
            modifier = Modifier.fillMaxSize(), // Imagen ocupa todo el tamaño de la caja
            contentScale = ContentScale.Crop // Ajusta la imagen para llenar la caja
        )
    }

    // El componente Tutorial se coloca encima de la imagen de fondo
    Tutorial()
}

/**
 * Vista previa de la pantalla de tutorial en orientación horizontal (landscape).
 * Muestra cómo se ve la `TutorialScreen` en un dispositivo Pixel 5 en modo horizontal.
 *
 * @see TutorialScreen Pantalla principal del tutorial que incluye una imagen de fondo y un componente adicional.
 */
@Composable
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
private fun Preview() = CalabozosCompuertasTheme { TutorialScreen() }