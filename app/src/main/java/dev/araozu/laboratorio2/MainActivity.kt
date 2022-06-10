package dev.araozu.laboratorio2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.araozu.laboratorio2.model.AppDatabase
import dev.araozu.laboratorio2.ui.theme.Proyecto1Theme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ejecutar coroutine sincrona
        runBlocking {
            // Crear nueva coroutine asincrona, en otro hilo
            // para inicializar la base de datos
            launch {
                AppDatabase.getDatabase(this@MainActivity)
            }
        }

        setContent {
            Proyecto1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost()
                }
            }
        }
    }
}

/**
 * Configura las rutas para cambiar entre interfaces
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destinations.DistritosScreen.route
        ) {
            composable(
                route = Destinations.CandidatosScreen.route
            ) {
                ListaCandidatosView("Candidatos",navController);
            }
            //-INICIO-CRUD CANDIDATOS
            composable(
                route = Destinations.CreateCandidatosScreen.route
            ) {
                crear(navController)
            }
            composable(
                route = Destinations.EditCandidatosScreen.route,
                arguments = listOf(navArgument("candidato") {
                    defaultValue ="Mariano Otazu Yana"
                })
            ) {
                val  candidato= it.arguments?.getString("candidato")
                requireNotNull(candidato)
                CandidatoEdit(candidato, navController)
            }
            //-FIN-CRUD CANDIDATOS
            composable(
                route = Destinations.DistritosScreen.route
            ) {
                ListDistritos(navController)
            }
            composable(
                route = Destinations.CandidatosDistritoScreen.route,
                arguments = listOf(navArgument("distrito") { defaultValue = "Arequipa" })
            ) {
                val distrito = it.arguments?.getString("distrito")
                requireNotNull(distrito)
                ListCandidatosDistrito(distrito, navController)
            }
            composable(
                route = Destinations.PartidosScreen.route
            ) {
                ListPartidos(navController)
            }
            //
            composable(
                route = Destinations.CandidatosPartidoScreen.route,
                arguments = listOf(navArgument("partido") {
                    defaultValue = "Arequipa_Tradicion_Futuro"
                })
            ) {
                val partido = it.arguments?.getString("partido")
                requireNotNull(partido)
                ListCandidatosPartido(partido, navController)
            }

            //CRUD PARTIDO
            composable(
                route = Destinations.PartidoCRUDScreen.route
            ) {
                PartidosCRUDScreen(navController)
            }
            //
            composable(
                route = Destinations.PartidoEditScreen.route,
                arguments = listOf(navArgument("partido") {
                    defaultValue = "Arequipa_Tradicion_Futuro"
                })
            ) {
                val partido = it.arguments?.getString("partido")
                requireNotNull(partido)
                PartidoEditScreen(partido, navController)
            }
        }
    }

}


// Bottom Navigation

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object CandidatosBottom :
        BottomNavItem("Candidatos", R.drawable.ic_district, Destinations.CandidatosScreen.route)

    object DistritosBottom :
        BottomNavItem("Distritos", R.drawable.ic_district, Destinations.DistritosScreen.route)

    object PartidosBottom :
        BottomNavItem("Partidos", R.drawable.ic_party, Destinations.PartidosScreen.route)

    object PartidosCRUDBottom :
        BottomNavItem("P_CRUD", R.drawable.ic_pencil, Destinations.PartidoCRUDScreen.route)

}

@Composable
fun BottomNavigation(navController: NavController) {
   val items = listOf(
        BottomNavItem.DistritosBottom,
        BottomNavItem.PartidosBottom,
        BottomNavItem.CandidatosBottom,
        BottomNavItem.PartidosCRUDBottom
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

