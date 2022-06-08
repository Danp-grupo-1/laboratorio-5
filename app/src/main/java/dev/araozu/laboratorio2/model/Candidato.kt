package dev.araozu.laboratorio2.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Dao
interface CandidatoDao {

}
