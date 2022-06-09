package dev.araozu.laboratorio2

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.model.Partido
import kotlinx.coroutines.launch

@Composable
fun PartidosCRUDScreen(
    navController: NavController
) {
    val ctx = LocalContext.current
    val s = rememberCoroutineScope()

    var listaPartido by remember {
        mutableStateOf(listOf<Partido>())
    }

    LaunchedEffect(s){
        val db = AppDatabase.getDatabase(ctx)
        listaPartido = db.partidoDao().getAll()
    }


    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFab(navController= navController)
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                partidos = listaPartido,
                navController
            )
        }
    )
}



@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    partidos: List<Partido>,
    navController: NavController
){
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn{
            items(partidos){ partido ->
                PartidoItem(
                    partido = partido,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun HomeFab(
    modifier: Modifier = Modifier,
    navController: NavController
){
    FloatingActionButton(
        onClick = {
            navController.navigate(route = Destinations.PartidoEditScreen.createRoute("n"))
        },
        modifier = modifier
            .height(260.dp)
            .padding(110.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = "ADD USER")
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = "CRUD",
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}


