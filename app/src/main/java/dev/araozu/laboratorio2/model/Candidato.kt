package dev.araozu.laboratorio2.model

import androidx.annotation.DrawableRes

data class Candidato(
    val nombre: String,
    val partido: Partido,
    /**
     * Una url a una foto
     */
    val foto: String,
    val biografia: String,
    val distrito: Distrito,
)
