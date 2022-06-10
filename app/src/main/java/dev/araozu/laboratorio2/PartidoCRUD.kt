package dev.araozu.laboratorio2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaPartidoView(
    partido: Partido,
    navController: NavController
) {
    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()

    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .width(35.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = partido.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(
                            route = Destinations.EditPartidoScreen.createRoute(
                                partido.nombre
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar Partido"
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                IconButton(
                    onClick = {
                        couritineScope.launch {
                            val db = AppDatabase.getDatabase(ctx)
                            db.partidoDao().delete(partido)

                        }
                        navController.navigate(
                            route = Destinations.PartidoCRUDScreen.route
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar Partido"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPartidosView(
    titulo: String,
    navController: NavController
) {

    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    var listaPartidos by remember {
        mutableStateOf(listOf<Partido>())
    }

    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        listaPartidos = db.partidoDao().getAll()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .padding(horizontal = 10.dp),
        floatingActionButton = { FABPartido(navController) },
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
                items(listaPartidos) {
                    TarjetaPartidoView(it, navController)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidoEdit(
    partido: String,
    navController: NavController
) {

    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()
    val s = rememberCoroutineScope()

    var partidoRecuperado by remember {
        mutableStateOf(Partido("", 4, "", ""))
    }

    LaunchedEffect(s) {
        val db = AppDatabase.getDatabase(ctx)
        partidoRecuperado = db.partidoDao().getByName(partido)
    }
    if (partidoRecuperado.nombre != "") {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Editar partido") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigate(
                                    route = Destinations.PartidoCRUDScreen.route
                                )
                            }
                        ) {
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


                    var nombre by remember {
                        mutableStateOf(partidoRecuperado.nombre)
                    }
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = nombre,
                        onValueChange = { newText ->
                            nombre = newText

                        },
                        label = {
                            Text(text = "Nombre partido")
                        })

                    Spacer(modifier = Modifier.size(10.dp))

                    var fundacion by remember {
                        mutableStateOf(partidoRecuperado.fundacion.toString())
                    }
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = fundacion,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { newText ->
                            fundacion = newText

                        },
                        label = {
                            Text(text = "Fundacion partido")
                        })

                    Spacer(modifier = Modifier.size(10.dp))


                    var domicilio by remember {
                        mutableStateOf(partidoRecuperado.domicilio)
                    }
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = domicilio,
                        onValueChange = { newText ->
                            domicilio = newText

                        },
                        label = {
                            Text(text = "Domicilio partido")
                        })

                    Spacer(modifier = Modifier.size(10.dp))

                    var imagen by remember {
                        mutableStateOf(partidoRecuperado.imagen)
                    }
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = imagen,
                        onValueChange = { newText ->
                            imagen = newText

                        },
                        label = {
                            Text(text = "Imagen partido")
                        })

                    Spacer(modifier = Modifier.size(10.dp))

                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            couritineScope.launch {
                                val db = AppDatabase.getDatabase(ctx)
                                partidoRecuperado.nombre = nombre

                                partidoRecuperado.domicilio = domicilio
                                partidoRecuperado.imagen = imagen
                                db.partidoDao().update(partidoRecuperado)
                            }
                            navController.navigate(
                                route = Destinations.PartidoCRUDScreen.route
                            )
                        }
                    ) {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidoCreate(
    navController: NavController
) {

    val ctx = LocalContext.current
    val couritineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Crear partido") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            route = Destinations.PartidoCRUDScreen.route
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
                //Vista
                var nombre by remember {
                    mutableStateOf("")
                }
                TextField(modifier = Modifier
                    .fillMaxWidth(), value = nombre, onValueChange = { newText ->
                    nombre = newText
                }, label = {
                    Text(text = "Nombre Partido")
                })

                Spacer(modifier = Modifier.size(10.dp))

                var fundacion by remember {
                    mutableStateOf("")
                }
                TextField(modifier = Modifier
                    .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = fundacion,
                    onValueChange = { newText ->
                        fundacion = newText
                    },
                    label = {
                        Text(text = "Fundacion Partido")
                    })

                Spacer(modifier = Modifier.size(10.dp))

                var domicilio by remember {
                    mutableStateOf("")
                }
                TextField(modifier = Modifier
                    .fillMaxWidth(), value = domicilio, onValueChange = { newText ->
                    domicilio = newText
                }, label = {
                    Text(text = "Domicilio Partido")
                })

                Spacer(modifier = Modifier.size(10.dp))

                var imagen by remember {
                    mutableStateOf("")
                }
                TextField(modifier = Modifier
                    .fillMaxWidth(), value = imagen, onValueChange = { newText ->
                    imagen = newText
                }, label = {
                    Text(text = "Imagen Partido")
                })

                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        couritineScope.launch {
                            val db = AppDatabase.getDatabase(ctx)
                            val partido = Partido(nombre, fundacion.toInt(), domicilio, imagen)
                            db.partidoDao().insertAll(partido)
                        }

                        navController.navigate(route = Destinations.PartidoCRUDScreen.route)
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

@Composable
fun FABPartido(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(
                route = Destinations.CreatePartidoScreen.route
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
