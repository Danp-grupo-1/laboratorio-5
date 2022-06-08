package dev.araozu.laboratorio2.model

import androidx.room.*

@Entity(
    tableName = "candidatos",
)
data class Candidato(
    @ColumnInfo(name = "nombre") val nombre: String,
    // Id del partido en Room
    @ColumnInfo(name = "partido") val partido: String,
    /**
     * Una url a una foto
     */
    @ColumnInfo(name = "foto") val foto: String,
    @ColumnInfo(name = "biografia") val biografia: String,
    @ColumnInfo(name = "distrito") val distrito: Distrito,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {
    constructor(
        nombre: String,
        partido: Partido,
        foto: String,
        biografia: String,
        distrito: Distrito
    ) : this(nombre, partido.nombre, foto, biografia, distrito)

    class Converter {
        @TypeConverter
        fun fromDistrito(d: Distrito): String {
            return d.toString()
        }

        @TypeConverter
        fun toDistrito(s: String): Distrito {
            return Distrito.fromString(s)!!
        }
    }
}

@Dao
interface CandidatoDao {
    @Query("SELECT * FROM candidatos")
    suspend fun getAll(): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE nombre = :nombre")
    suspend fun getByNombre(nombre: String): Candidato

    @Query("SELECT * FROM candidatos WHERE partido = :partido")
    suspend fun getByPartido(partido: String): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE distrito = :distrito")
    suspend fun getByDistrito(distrito: Distrito): List<Candidato>

    @Insert
    suspend fun insertAll(vararg candidato: Candidato)

    @Delete
    suspend fun delete(candidato: Candidato)
}
