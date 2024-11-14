package com.example.calabozoscompuertas.ui.screens.book.pags

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calabozoscompuertas.R
import com.example.calabozoscompuertas.ui.navcontroller.Routes

/**
 * Pantalla de créditos del libro interactivo.
 *
 * Esta pantalla muestra la información de los desarrolladores, los gráficos y los agradecimientos
 * relacionados con la creación de la aplicación. Además, incluye un área de navegación que permite al usuario
 * regresar a la pantalla de inicio al hacer clic en cualquier parte de la pantalla.
 *
 * @param navController Controlador de navegación utilizado para cambiar entre las pantallas.
 */
@Composable
fun CreditsScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize() // La caja ocupa todo el espacio disponible de la pantalla.
            .background(Color.Black) // Fondo negro para la pantalla de créditos.
            .padding(16.dp) // Espaciado interno de 16dp
            .clickable {
                // Acción al hacer clic: regresa a la pantalla de inicio.
                navController.popBackStack()
                navController.navigate(Routes.Start.route)
            }
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState), // Hace que el contenido sea desplazable.
            horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente.
        ) {
            // Información del desarrollador.
            Text(text = "Developed by", color = Color.Gray)
            Text(text = "Christopher Alejandro Maldonado Chavez", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre las secciones de texto.

            // Información sobre los gráficos.
            Text(text = "Graphics", color = Color.Gray)
            Text(text = "Zabdiel Rios Cervantes", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            // Agradecimientos.
            Text(text = "Acknowledgements", color = Color.Gray)
            Text(text = "Juan Manuel Moreno Garcia", color = Color.White)
            Text(text = "Jesus Omar Raya Morales", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            // Información adicional con logo y texto.
            Row(
                modifier = Modifier
                    .fillMaxWidth() // La fila ocupa todo el ancho disponible.
                    .wrapContentHeight(), // La altura de la fila es ajustada al contenido.
                verticalAlignment = Alignment.CenterVertically, // Alinea los elementos verticalmente en el centro.
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center // Centra los elementos horizontalmente.
            ) {
                Text(
                    text = "Power By", // Texto antes del logo.
                    fontWeight = FontWeight.Bold, // El texto está en negritas.
                    fontSize = 16.sp, // Tamaño de la fuente es 16sp.
                    color = colorScheme.tertiary // Usamos un color terciario del tema.
                )
                Icon(
                    painter = painterResource(R.drawable.logo_swiftid_centrado), // Carga el logo desde los recursos.
                    tint = colorScheme.tertiary, // El color del logo es terciario del tema.
                    contentDescription = "Logo de SwiftID", // Descripción para accesibilidad.
                    modifier = Modifier.size(100.dp) // Tamaño del logo es de 100dp.
                )
                Text(
                    text = "Override", // Texto después del logo.
                    fontWeight = FontWeight.Bold, // El texto está en negritas.
                    fontSize = 16.sp, // Tamaño de la fuente es 16sp.
                    color = colorScheme.tertiary // Usamos el color terciario del tema.
                )
            }
        }
    }
}