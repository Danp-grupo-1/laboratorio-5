package dev.araozu.laboratorio2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PartidoEditScreen(
    partido: String,
    navController: NavController
) {
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    var listaPartido by remember {
        mutableStateOf(Partido("no",1,"recoje","valor"))
    }

    LaunchedEffect(s){
        val db = AppDatabase.getDatabase(ctx)
        listaPartido = db.partidoDao().getByName(partido)
    }


    Scaffold(
        topBar = {
            EditTopBar(
                topAppBarText = "Agregar Partido"
            )
        },
        content = {
            if(partido.equals("n")){
                AddContent(
                    navController
                )
            }else{
                EditContent(
                    listaPartido.nombre,
                    listaPartido.fundacion.toString(),
                    listaPartido.domicilio,
                    listaPartido.imagen,
                    navController
                )
            }

        },
    )
}

@Composable
fun EditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun EditContent(
    nombre: String,
    fundacion: String,
    domicilio: String,
    imagen: String,
    navController: NavController
) {
    val ctx = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember {
        mutableStateOf( nombre)
    }
    var fundacion by remember {
        mutableStateOf(fundacion)
    }
    var domicilio by remember {
        mutableStateOf( domicilio)
    }
    var imagen by remember {
        mutableStateOf( imagen)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        TextField(
            value = nombre,
            onValueChange = {
            newText -> nombre = newText
        } )
        TextField(
            value = fundacion,
            onValueChange ={
                    newText -> fundacion = newText
            } )
        TextField(
            value = domicilio,
            onValueChange ={
                    newText -> domicilio = newText
            } )
        TextField(
            value = imagen,
            onValueChange ={
                    newText -> imagen = newText
            } )


        Button(
            onClick = {
                coroutineScope.launch{
                    val db = AppDatabase.getDatabase(ctx)
                    val partido = Partido(
                        nombre,
                        fundacion.toInt(),
                        domicilio,
                        imagen
                    )
                    db.partidoDao().update(partido)
                }
                navController.navigate(Destinations.PartidoCRUDScreen.route)
            }
        ) {
            Text(text = "Actualizar")
        }
    }
}


@Composable
fun AddContent(
    navController: NavController
) {
    val ctx = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember {
        mutableStateOf( "")
    }
    var fundacion by remember {
        mutableStateOf( "")
    }
    var domicilio by remember {
        mutableStateOf( "")
    }
    var imagen by remember {
        mutableStateOf( "")
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        TextField(
            value = nombre,
            onValueChange = {
                    newText -> nombre = newText
            } )
        TextField(
            value = fundacion,
            onValueChange ={
                    newText -> fundacion = newText.filter { it.isDigit() }
            } )
        TextField(
            value = domicilio,
            onValueChange ={
                    newText -> domicilio = newText
            } )
        TextField(
            value = imagen,
            onValueChange ={
                    newText -> imagen = newText
            } )


        Button(
            onClick = {
                coroutineScope.launch{
                    val db = AppDatabase.getDatabase(ctx)
                    val partido = Partido(
                        nombre,
                        fundacion.toInt(),
                        domicilio,
                        imagen
                    )
                    db.partidoDao().insertAll(partido)
                }
                navController.navigate(Destinations.PartidoCRUDScreen.route)
            }
        ) {
            Text(text = "Agregar")
        }
    }
}


@Composable
fun PartidoItem(
    modifier: Modifier = Modifier,
    partido: Partido,
    navController: NavController
){
    val ctx = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${partido.nombre}",
                    style = MaterialTheme.typography.h6
                )
            }
            Row{
                IconButton(onClick = {
                    navController.navigate(route = Destinations.PartidoEditScreen.createRoute(partido.nombre))
                } ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            val db = AppDatabase.getDatabase(ctx)
                            db.partidoDao().delete(partido)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}