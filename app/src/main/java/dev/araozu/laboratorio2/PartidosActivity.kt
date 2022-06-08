package dev.araozu.laboratorio2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.launch

@Composable
fun BotonPartido(partido: Partido, navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    route = Destinations.CandidatosPartidoScreen.createRoute(
                        partido.nombre
                    )
                )
            }
        ) {
            Text(
                text = partido.nombre,
                style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}

/**
 * Renderiza una lista de botones con los partidos políticos
 */
@Composable
fun ListPartidos(navController: NavController) {
    val ctx2 = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var lista by remember { mutableStateOf(listOf<Partido>()) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        coroutineScope.launch {
            val db = AppDatabase.getDatabase(ctx2)
            lista = db.partidoDao().getAll()
        }

        item {
            Text(
                text = "Buscar por partido político",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        items(lista) {
            BotonPartido(it, navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
