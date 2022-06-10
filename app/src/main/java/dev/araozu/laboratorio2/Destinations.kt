package dev.araozu.laboratorio2

import dev.araozu.laboratorio2.model.Candidato


sealed class Destinations(val route: String) {
    object DistritosScreen : Destinations("distritos_screen")
    object CandidatosScreen : Destinations("candidatos_screen")
    object CreateCandidatosScreen : Destinations("create_candidatos_screen")
    object EditCandidatosScreen : Destinations( "candidatos_edit_screen/?candidato={candidato}") {
        fun createRoute(candidato: String) = "candidatos_edit_screen/?candidato=$candidato"
    }
    object PartidosScreen : Destinations("partidos_screen")
    object CandidatosDistritoScreen : Destinations("candidatos_screen/?distrito={distrito}") {
        fun createRoute(distrito: String) = "candidatos_screen/?distrito=$distrito"
    }

    object CandidatosPartidoScreen : Destinations("candidatos_screen/?partido={partido}") {
        fun createRoute(partido: String) = "candidatos_screen/?partido=$partido"
    }

    //CRUD
    object PartidoCRUDScreen : Destinations("partidos_crud_screen")
    object PartidoEditScreen : Destinations("partidos_edit_screen/?partido={partido}") {
        fun createRoute(partido: String) = "partidos_edit_screen/?partido=$partido"
    }
}
