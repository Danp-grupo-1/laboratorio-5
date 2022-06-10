package dev.araozu.laboratorio2

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Candidato
import dev.araozu.laboratorio2.model.Distrito
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.launch


/*
*
* INICIO  CRUD  CANDIDATOS
*
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaCandidatoView(candidato: Candidato, navController: NavController) {
    // Contexto
    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = candidato.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)

            ) {
                IconButton(onClick = {
                    navController.navigate(
                        route = Destinations.EditCandidatosScreen.createRoute(
                            candidato.nombre
                        )
                    )
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar Candidato"
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                IconButton(onClick = {
                    couritineScope.launch {
                        val db = AppDatabase.getDatabase(ctx)
                        db.candidatoDao().delete(candidato)

                    }
                    navController.navigate(
                        route = Destinations.CandidatosScreen.route
                    )
                    Toast.makeText(
                        ctx,
                        "Candidato ${candidato.nombre} eliminado",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar Candidato"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCandidatosView(titulo: String, navController: NavController) {
    // contexto
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()
    // inicializar
    var listaCandidato by remember {
        mutableStateOf(listOf<Candidato>())
    }
    // llenar la lista con la BD
    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        listaCandidato = db.candidatoDao().getAll()
    }

    // Diseño vista
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .padding(horizontal = 10.dp),
        floatingActionButton = { FAB(navController) },
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = titulo,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                },
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(listaCandidato) {
                    TarjetaCandidatoView(it, navController)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    )
}

// Crear boton flotante
@Composable
fun FAB(navController: NavController) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            navController.navigate(
                route = Destinations.CreateCandidatosScreen.route
            )
        },
        contentColor = Color.White,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Icon"
        )
    }
}

// Crear candidato
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun crear(navController: NavController) {
    // Inicializar lista de distritos
    val distritos = Distrito.values()

    // Contexto
    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Crear candidato") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            route = Destinations.CandidatosScreen.route
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )

        },
        content = { pad ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(pad),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Vista
                var nombre by remember {
                    mutableStateOf(" ")
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = nombre,
                    onValueChange = { newText ->
                        nombre = newText
                    },
                    label = {
                        Text(text = "Nombre candidato")
                    }
                )

                Spacer(modifier = Modifier.size(10.dp))

                var foto by remember {
                    mutableStateOf(" ")
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = foto,
                    onValueChange = { newText ->
                        foto = newText
                    },
                    label = {
                        Text(text = "Foto")
                    }
                )

                Spacer(modifier = Modifier.size(10.dp))

                var biografia by remember {
                    mutableStateOf(" ")
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = biografia,
                    onValueChange = { newText ->
                        biografia = newText
                    },
                    label = {
                        Text(text = "Biografia")
                    }
                )

                Spacer(modifier = Modifier.size(10.dp))

                // Obtenemos el valor seleccionado del dropdown
                var distrito = DistritoSelection(distritos = distritos, Distrito.TIABAYA)

                Spacer(modifier = Modifier.size(10.dp))

                var partido = PartidoSelection("Arequipa Transformacion")

                // Boton para guardar el nuevo candidato
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        couritineScope.launch {
                            val db = AppDatabase.getDatabase(ctx)
                            val candidato = Candidato(
                                nombre,
                                partido,
                                foto,
                                biografia,
                                distrito
                            )

                            db.candidatoDao().insertAll(candidato)
                        }

                        // Retorna a la lista de candidatos(vista)
                        navController.navigate(
                            route = Destinations.CandidatosScreen.route
                        )
                    }
                ) {
                    Text(
                        text = "Guardar",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }
        }
    )
}


// Creacion de Dropdown o spinner para distrito
@Composable
fun DistritoSelection(distritos: Array<Distrito>, d: Distrito): Distrito {
    var distritoName: Distrito by remember { mutableStateOf(d) }
    var expanded by remember { mutableStateOf(false) }

    // diseño
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .padding(30.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = distritoName.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                distritos.forEach { distrito ->
                    DropdownMenuItem(
                        text = {
                            Text(text = distrito.name)
                        },
                        onClick = {
                            expanded = false
                            distritoName = distrito
                        })
                }
            }
        }
    }

    // devolvemos el distrito
    return distritoName
}

// Creacion de Dropdown o spinner para partidos
@Composable
fun PartidoSelection(partido: String): String {
    // Contexto
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    // Inicializar lista de partidos
    var partidos by remember { mutableStateOf(listOf<Partido>()) }
    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        partidos = db.partidoDao().getAll()

    }

    // Si no hay datos en la lista partidos no se visualiza nada
    if (partidos.isNotEmpty()) {
        var partidoName: String by remember { mutableStateOf(partido) }
        var expanded by remember { mutableStateOf(false) }

        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier
                    .padding(30.dp)
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = partidoName,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")


                DropdownMenu(expanded = expanded, onDismissRequest = {
                    expanded = false
                }) {
                    partidos.forEach { partido ->
                        DropdownMenuItem(
                            text = {
                                Text(text = partido.nombre)
                            },
                            onClick = {
                                expanded = false
                                partidoName = partido.nombre
                            }
                        )
                    }
                }
            }
        }

        // Devolvemos el nombre del partido
        return partidoName
    }

    // Devolvemos  vacio sino cumple con el if
    return ""
}

// Editar candidato
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidatoEdit(candidato: String, navController: NavController) {
    // Inicializar lista de distritos
    val distritos = Distrito.values()

    // Contexto
    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()
    val s = rememberCoroutineScope()

    var candidatoRecuperado by remember {
        mutableStateOf(Candidato("", "        ", "", "", Distrito.TIABAYA))
    }

    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        candidatoRecuperado = db.candidatoDao().getByNombre(candidato)
    }
    if (candidatoRecuperado.nombre != "") {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Editar candidato") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(
                                route = Destinations.CandidatosScreen.route
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
            content = { pad ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(horizontal = 16.dp).padding(vertical = 20.dp),
                        .padding(pad),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    //Vista
                    var nombre by remember {
                        mutableStateOf(candidatoRecuperado.nombre)
                    }
                    TextField(modifier = Modifier.fillMaxWidth(),
                        value = nombre,
                        onValueChange = { newText ->
                            nombre = newText
                        },
                        label = {
                            Text(text = "Nombre candidato")
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    var foto by remember {
                        mutableStateOf(candidatoRecuperado.foto)
                    }
                    TextField(modifier = Modifier.fillMaxWidth(),
                        value = foto,
                        onValueChange = { newText ->
                            foto = newText
                        },
                        label = {
                            Text(text = "Foto")
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    var biografia by remember {
                        mutableStateOf(candidatoRecuperado.biografia)
                    }
                    TextField(modifier = Modifier.fillMaxWidth(),
                        value = biografia,
                        onValueChange = { newText ->
                            biografia = newText
                        },
                        label = {
                            Text(text = "Biografia")
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    // Obtenemos el valor seleccionado del dropdown
                    var distrito =
                        DistritoSelection(distritos = distritos, candidatoRecuperado.distrito)

                    Spacer(modifier = Modifier.size(10.dp))

                    var partido = PartidoSelection(candidatoRecuperado.partido)

                    // Boton para guardar el nuevo candidato
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            couritineScope.launch {
                                val db = AppDatabase.getDatabase(ctx)
                                candidatoRecuperado.nombre = nombre
                                candidatoRecuperado.distrito = distrito
                                candidatoRecuperado.partido = partido
                                candidatoRecuperado.foto = foto
                                candidatoRecuperado.biografia = biografia
                                db.candidatoDao().update(candidatoRecuperado)
                            }
                            // Retorna a la lista de candidatos(vista)
                            navController.navigate(
                                route = Destinations.CandidatosScreen.route
                            )
                        }) {
                        Text(
                            text = "Actualizar",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }
                }
            },
        )
    }
}
