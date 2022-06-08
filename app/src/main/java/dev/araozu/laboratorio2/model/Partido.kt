package dev.araozu.laboratorio2.model

import androidx.room.*
import java.lang.IllegalArgumentException

@Entity(tableName = "partidos")
data class Partido_(
    @PrimaryKey @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "fundacion") val fundacion: Int,
    @ColumnInfo(name = "domicilia") val domicilio: String,
    @ColumnInfo(name = "imagen") val imagen: String
)

@Dao
interface PartidoDao {
    @Query("SELECT * FROM partidos")
    fun getAll(): List<Partido_>

    @Query("SELECT * FROM partidos WHERE nombre = :name")
    fun getByName(name: String): Partido_

    @Insert
    fun insertAll(vararg partidos: Partido_)

    @Delete
    fun delete(partido: Partido_)
}


val Arequipa_Tradicion_Futuro = Partido_(
    nombre = "Arequipa Tradicion y Futuro",
    fundacion = 2005,
    domicilio = "CALLE REPUBLICA DE CHILE N° 323 Dpto. 202",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/142",
)
val Arequipa_transformacion = Partido_(
    nombre = "Arequipa Transformacion",
    fundacion = 2015,
    domicilio = "CALLE CORBACHO. URB. LA LIBERTAD N° 304",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2313",
)
val Frente_Popular_Agricola_del_Peru = Partido_(
    nombre = "Frente Popular Agricola del Peru",
    fundacion = 2020,
    domicilio = "",
    imagen = "",
)
val Movimiento_Regional_Reveladora = Partido_(
    nombre = "Movimiento Regional Reveladora",
    fundacion = 2020,
    domicilio = "-",
    imagen = "",
)
val Partido_Politico_Nacional_Peru_Libre = Partido_(
    nombre = "Partido Politico Nacional Peru Libre",
    fundacion = 2016,
    domicilio = "AV. BRASIL N° 170 Dpto. 3er piso - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2218",
)
val Yo_Arequipa = Partido_(
    nombre = "Yo Arequipa",
    fundacion = 2018,
    domicilio = "AV. VILLA GLORIA BLOCK T1 OFICINA 203 CH MANUEL POLO JIMENEZ",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2744",
)
val Movimineto_Regional_Arequipa_Avancemos = Partido_(
    nombre = "Movimiento Regional Arequipa Avancemos",
    fundacion = 2014,
    domicilio = "AV. PUMACAHUA N° 202",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2307",
)
val Avanza_Pais_Partido_De_Integracion_Social = Partido_(
    nombre = "Avanza Pais Partido De Integracion Social",
    fundacion = 2017,
    domicilio = "AV. NAZCA N° 167 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2173",
)
val Juntos_Por_El_Desarrollo_De_Arequipa = Partido_(
    nombre = "Juntos Por El Desarrollo De Arequipa",
    fundacion = 2014,
    domicilio = "CALLE COOPERATIVA CLISA C-4 N° S/N",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2257",
)
val Accion_Popular = Partido_(
    nombre = "Accion Popular",
    fundacion = 2004,
    domicilio = "AV. 9 DE DICIEMBRE (EX PASEO COLON) N° 218 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/4",
)
val Partido_Morado = Partido_(
    nombre = "Partido Morado",
    fundacion = 2019,
    domicilio = "CALLE IGNACIO MERINO N° 375 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2840",
)
val Fe_En_El_Peru =
    Partido_(nombre = "Fe En El Peru", fundacion = 2020, domicilio = "-", imagen = "")
val Partido_Frente_De_La_Esperanza_2021 = Partido_(
    nombre = "Partido Frente De La Esperanza 2021",
    fundacion = 2020,
    domicilio = "-",
    imagen = "",
)
val Alianza_Para_El_Progreso = Partido_(
    nombre = "Alianza Para El Progreso",
    fundacion = 2008,
    domicilio = "AV. DE LA POLICIA N° 643 - LIMA - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/1257",
)
val Fuerza_Arequipenya = Partido_(
    nombre = "Fuerza Arequipeña",
    fundacion = 2010,
    domicilio = "CALLE Manuel Ugarteche, Selva Alegre N° 517",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/1393",
)
val Movimiento_Regional_Del_Ajo_Justicia_Y_Orden = Partido_(
    nombre = "Movimiento Regional Del Ajo Justicia Y Orden",
    fundacion = 2020,
    domicilio = "-",
    imagen = "",
)
val Juntos_Por_El_Peru = Partido_(
    nombre = "Juntos Por El Peru",
    fundacion = 2009,
    domicilio = "AV. Arequipa N° 330 Int. 1001 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/1264",
)
val Arequipa_Unidos_Por_El_Gran_Cambio = Partido_(
    nombre = "Arequipa Unidos Por El Gran Cambio",
    fundacion = 2013,
    domicilio = "CALLE LA MERCED N° 113",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2261",
)
val Partido_Democratico_Somos_Peru = Partido_(
    nombre = "Partido Democratico Somos Peru",
    fundacion = 2004,
    domicilio = "JR. TORRE DE LA MERCED N° 165-167, URB. SANTA CATALINA - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/14",
)
val Podemos_Peru = Partido_(
    nombre = "Podemos Peru",
    fundacion = 2018,
    domicilio = "AV. PASEO COLON N° 323 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/2731",
)
val Fuerza_Popular = Partido_(
    nombre = "Fuerza Popular",
    fundacion = 2010,
    domicilio = "AV. 9 DE DICIEMBRE N° 422 - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/1366",
)
val Arequipa_Renace = Partido_(
    nombre = "Arequipa Renace",
    fundacion = 2006,
    domicilio = "CALLE Colón N° 131-B, oficina 01, primer piso N° 131-B Dpto. 01",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/443",
)
val Renovacion_Popular = Partido_(
    nombre = "Renovacion Popular",
    fundacion = 2004,
    domicilio = "CALLE COSTA RICA N° 157, URB. LOS PATRICIOS - LIMA",
    imagen = "https://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Simbolo/GetSimbolo/22",
)

var partidos = arrayListOf(
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
)

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
            Arequipa_Renace -> "Arequipa Renace"
            Renovacion_Popular -> "Renovacion Popular"
            NINGUNO -> ""
        }
    }

}
