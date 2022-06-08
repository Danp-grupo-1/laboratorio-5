package dev.araozu.laboratorio2.model

import androidx.room.*

@Entity(
    tableName = "candidatos",
)
data class Candidato(
    @PrimaryKey @ColumnInfo(name = "nombre") val nombre: String,
    // Id del partido en Room
    @ColumnInfo(name = "partido") val partido: String,
    /**
     * Una url a una foto
     */
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "biografia") val biografia: String,
    @ColumnInfo(name = "distrito") val distrito: Distrito,
) {
    constructor(
        nombre: String,
        partido: Partido,
        foto: String,
        biografia: String,
        distrito: Distrito
    ) : this(nombre, partido.nombre, foto, biografia, distrito)
}

@Dao
interface CandidatoDao {
    @Query("SELECT * FROM candidatos")
    fun getAll(): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE nombre = :nombre")
    fun getByNombre(nombre: String): Candidato

    @Query("SELECT * FROM candidatos WHERE partido = :partido")
    fun getByPartido(partido: String): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE distrito = :distrito")
    fun getByDistrito(distrito: Distrito): List<Candidato>

    @Insert
    fun insertAll(vararg candidato: Candidato)

    @Delete
    fun delete(candidato: Candidato)
}
