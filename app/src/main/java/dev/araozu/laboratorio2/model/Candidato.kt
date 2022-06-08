package dev.araozu.laboratorio2.model

import androidx.room.*

@Entity(tableName = "candidatos")
data class Candidato(
    @PrimaryKey @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "partido") val partido: Partido,
    /**
     * Una url a una foto
     */
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "biografia") val biografia: String,
    @ColumnInfo(name = "distrito") val distrito: Distrito,
)

@Dao
interface CandidatoDao {
    @Query("SELECT * FROM candidatos")
    fun getAll(): List<Candidato>

    @Insert
    fun insertAll(vararg candidato: Candidato)

    @Delete
    fun delete(candidato: Candidato)
}
