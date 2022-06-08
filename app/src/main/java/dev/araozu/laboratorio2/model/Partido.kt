package dev.araozu.laboratorio2.model

import androidx.room.*
import java.lang.IllegalArgumentException

@Entity(tableName = "partido")
data class Partido_(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "fundacion") val fundacion: Int,
    @ColumnInfo(name = "domicilia") val domicilio: String,
    @ColumnInfo(name = "imagen") val imagen: String
)

@Dao
interface PartidoDao {
    @Query("SELECT * FROM partido")
    fun getAll(): List<Partido_>

    @Insert
    fun insertAll(vararg partidos: Partido_)

    @Delete
    fun delete(partido: Partido_)
}


enum class Partido {
    Arequipa_Tradicion_Futuro,
    Arequipa_transformacion,
    Frente_Popular_Agricola_del_Peru,
    Movimiento_Regional_Reveladora,
    Partido_Politico_Nacional_Peru_Libre,
    Yo_Arequipa,
    Movimineto_Regional_Arequipa_Avancemos,
    Avanza_Pais_Partido_De_Integracion_Social,
    Juntos_Por_El_Desarrollo_De_Arequipa,
    Accion_Popular,
    Partido_Morado,
    Fe_En_El_Peru,
    Partido_Frente_De_La_Esperanza_2021,
    Alianza_Para_El_Progreso,
    Fuerza_Arequipenya,
    Movimiento_Regional_Del_Ajo_Justicia_Y_Orden,
    Juntos_Por_El_Peru,
    Arequipa_Unidos_Por_El_Gran_Cambio,
    Partido_Democratico_Somos_Peru,
    Podemos_Peru,
    Fuerza_Popular,
    Arequipa_Renace,
    Renovacion_Popular,
    NINGUNO;

    companion object {
        fun fromString(partido: String): Partido {
            return try {
                valueOf(partido.replace(" ", "_"))
            } catch (e: IllegalArgumentException) {
                NINGUNO
            }
        }
    }

    override fun toString(): String {
        return when (this) {
            Arequipa_Tradicion_Futuro -> "Arequipa, Tradicion y Futuro"
            Arequipa_transformacion -> "Arequipa Transformacion"
            Frente_Popular_Agricola_del_Peru -> "Frente Popular Agricola del Peru"
            Movimiento_Regional_Reveladora -> "Movimiento Regional Reveladora"
            Partido_Politico_Nacional_Peru_Libre -> "Partido Politico Nacional Peru Libre"
            Yo_Arequipa -> "Yo Arequipa"
            Movimineto_Regional_Arequipa_Avancemos -> "Movimineto regional Arequipa Avancemos"
            Avanza_Pais_Partido_De_Integracion_Social -> "Avanza Pais - Partido De Integracion Social"
            Juntos_Por_El_Desarrollo_De_Arequipa -> "Juntos por el Desarrollo de Arequipa"
            Accion_Popular -> "Accion Popular"
            Partido_Morado -> "Partido Morado"
            Fe_En_El_Peru -> "Fe en el Peru"
            Partido_Frente_De_La_Esperanza_2021 -> "Partido Frente de la Esperanza 2021"
            Alianza_Para_El_Progreso -> "Alianza para el Progreso"
            Fuerza_Arequipenya -> "Fuerza Arequipeña"
            Movimiento_Regional_Del_Ajo_Justicia_Y_Orden -> "Movimiento Regional del Ajo Justicia y Orden"
            Juntos_Por_El_Peru -> "Juntos por el Peru"
            Partido_Democratico_Somos_Peru -> "Partido Democratico Somos Peru"
            Arequipa_Unidos_Por_El_Gran_Cambio -> "Arequipa-Unidos por el Gran Cambio"
            Podemos_Peru -> "Podemos Peru"
            Fuerza_Popular -> "Fuerza Popular"
            Arequipa_Renace->"Arequipa Renace"
            Renovacion_Popular->"Renovacion Popular"
            NINGUNO -> ""
        }
    }

}
