package dev.araozu.laboratorio2


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Candidato
import dev.araozu.laboratorio2.model.Distrito
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.launch


/**
 * Muestra una tarjeta de un candidato
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaCandidato(candidato: Candidato) {
    val tarjetaAbierta = remember { mutableStateOf(false) }

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            android.util.Log.d("TARJETA", "click, $tarjetaAbierta")
            tarjetaAbierta.value = !tarjetaAbierta.value
        }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            // modifier = Modifier.padding(horizontal = 10.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = candidato.foto),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .height(150.dp)
                // .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = candidato.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = candidato.partido.toString(),
                    fontWeight = FontWeight.Light,
                )
                Text(
                    text = candidato.distrito.toString(),
                    fontWeight = FontWeight.Light,
                )
            }
        }

        AnimatedVisibility(visible = tarjetaAbierta.value) {
            Text(
                text = candidato.biografia,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCandidatos(
    titulo: String,
    lista: List<Candidato>,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(titulo) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(lista) {
                    TarjetaCandidato(it)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    )
}

/**
 * Muestra una lista de candidatos filtrados segun un distrito
 */
@Composable
fun ListCandidatosDistrito(
    distritoStr: String,
    navController: NavController,
) {
    // Obtener el contexto, para pasar a la base de datos
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    val distrito = Distrito.fromString(distritoStr)!!

    // La lista de candidatos es reactiva, inicia vacia
    var listaCandidatos by remember { mutableStateOf(listOf<Candidato>()) }

    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        listaCandidatos = db.candidatoDao().getByDistrito(distrito)
    }

    ListaCandidatos(
        titulo = distrito.toString(),
        lista = listaCandidatos,
        onBack = {
            navController.navigate(
                route = Destinations.DistritosScreen.route
            )
        },
    )
}


/**
 * Muestra una lista de candidatos filtrados segun un partido politico
 */
@Composable
fun ListCandidatosPartido(
    partidoStr: String,
    navController: NavController,
) {
    // Obtener el contexto, para pasar a la base de datos
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    val partido = partidoStr

    // La lista de candidatos es reactiva, inicia vacia
    var listaCandidatos by remember { mutableStateOf(listOf<Candidato>()) }

    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        listaCandidatos = db.candidatoDao().getByPartido(partido)
    }

    ListaCandidatos(
        titulo = partido.toString(),
        lista = listaCandidatos,
        onBack = {
            navController.navigate(
                route = Destinations.PartidosScreen.route
            )
        },
    )
}





