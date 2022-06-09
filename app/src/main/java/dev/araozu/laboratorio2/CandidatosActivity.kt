package dev.araozu.laboratorio2

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
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
import dev.araozu.laboratorio2.ui.theme.Red100
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

/*
*
* VISTA CON CREACION DE CANDIDATOS
*
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaCandidatoView(candidato: Candidato) {
    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .width(35.dp),

        onClick = {

        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                ) {
                Text(
                    modifier=Modifier.padding(start = 10.dp),
                    text = candidato.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)

            ) { IconButton(onClick = {   }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Editar Candidato"
                )
            }}
            Column(
                horizontalAlignment = Alignment.End,

                ) {
                IconButton(onClick = {   }) {
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
fun ListaCandidatosView(titulo: String,navController: NavController) {
    //contexto
    val ctx= LocalContext.current
    val s= rememberCoroutineScope()
    //inicializar
    var listaCandidato by remember {
        mutableStateOf(listOf<Candidato>())
    }
    //llenar la lista con la BD
    LaunchedEffect(s){
        val db=AppDatabase.getDatabase(ctx)
        listaCandidato= db.candidatoDao().getAll()
    }


    //Diseño vista
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .padding(horizontal = 10.dp),
        floatingActionButton = { FAB(navController)},
        topBar = {
            SmallTopAppBar(
                title = { Text(titulo) },
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(listaCandidato) {
                    TarjetaCandidatoView(it)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    )
}
//Crear boton flotante
@Composable
fun FAB(navController: NavController){
    val context= LocalContext.current
    androidx.compose.material.FloatingActionButton(
        onClick = {
            navController.navigate(
                route = Destinations.CreateCandidatosScreen.route
            )
        }, backgroundColor = Red100,
        contentColor = Color.White,
    ) {
        androidx.compose.material.Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Icon"
        )

    }


}

//Crear candidato
@Composable
fun crear(navController: NavController ){
    val par= listOf<String>()

    //Contexto
    val ctx= LocalContext.current
    val couritineScope= rememberCoroutineScope()
    val s= rememberCoroutineScope()

    //Inicializar lista de distritos
    val distritos=Distrito.values()

    //Inicializar lista de partidos
    var listaPartidos   by remember { mutableStateOf(listOf<Partido>()) }
    /*  LaunchedEffect(s){
          val db = AppDatabase.getDatabase(ctx)
          listaPartidos  = db.partidoDao().getAll()
      }
      Log.d("cantidad de partidos",listaPartidos.size.toString())
  */
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center){
        var nombre by remember {
            mutableStateOf(" ")
        }
        TextField(value=nombre,onValueChange={ newText->
            nombre=newText
        },
            label={
                Text(text="Nombre candidato")
            })

        Spacer(modifier = Modifier.size(10.dp))

        var foto by remember {
            mutableStateOf(" ")
        }
        TextField(value=foto ,onValueChange={ newText->
            foto =newText
        },
            label={
                Text(text="Foto")
            })

        Spacer(modifier = Modifier.size(10.dp))

        var biografia by remember {
            mutableStateOf(" ")
        }
        TextField(value=biografia ,onValueChange={ newText->
            biografia =newText
        },
            label={
                Text(text="Biografia")
            })

        Spacer(modifier = Modifier.size(10.dp))

        //Obtenemos el valor seleccionado del dropdown
       var distrito= DistritoSelection(distritos = distritos)

        Spacer(modifier = Modifier.size(10.dp))


        //Obtenemos el valor seleccionado del dropdown
      // var partido= PartidoSelection(partidos =listaPartidos  )

        //Boton para guardar el nuevo candidato
        FilledTonalButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                /*couritineScope.launch {
                    val db=AppDatabase.getDatabase(ctx)
                    val candidato=Candidato(nombre,partido, foto, biografia, distrito)
                    db.candidatoDao().insertAll(candidato)
                }*/
                Log.d("Nombre",nombre)
                Log.d("Foto",foto)
                Log.d("Biografia",biografia)
                Log.d("Distrito",distrito.name)
              // Log.d("Partido",partido)
               //Retorna a la lista de candidatos(vista)
                navController.navigate(
                    route = Destinations.CandidatosScreen.route )}) {
            Text(
                text = "Guardar",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

// Creacion de Dropdown o spinner para distrito
@Composable
fun DistritoSelection(distritos:Array<Distrito>):Distrito {

    var distritoName:Distrito by remember { mutableStateOf(distritos[0] ) }
    var expanded by remember { mutableStateOf(false) }
    //diseño
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


            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
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
    //devolvemos el distrito
return  distritoName
}

// Creacion de Dropdown o spinner para partidos
@Composable
fun PartidoSelection(partidos:List<Partido>):String {

    var partidoName: String by remember { mutableStateOf(partidos[0].nombre) }
    var expanded by remember { mutableStateOf(false) }

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
                        })
                }
            }
        }
    }
    //Devolvemos el nombre del partido
    return  partidoName
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    }

