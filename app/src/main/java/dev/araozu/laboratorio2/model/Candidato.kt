package dev.araozu.laboratorio2.model

import androidx.room.*

@Dao
interface CandidatoDao {
    @Query("SELECT * FROM candidatos ORDER BY nombre ASC")
    suspend fun getAll(): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE nombre = :nombre")
    suspend fun getByNombre(nombre: String): Candidato

    @Query("SELECT * FROM candidatos WHERE partido = :partido")
    suspend fun getByPartido(partido: String): List<Candidato>

    @Query("SELECT * FROM candidatos WHERE distrito = :distrito")
    suspend fun getByDistrito(distrito: Distrito): List<Candidato>

    @Insert
    suspend fun insertAll(vararg candidato: Candidato)

    @Update
    suspend fun update(candidato: Candidato)

    @Delete
    suspend fun delete(candidato: Candidato)

    @Query("DELETE FROM candidatos")
    suspend fun deleteAll()
}

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
    // Constructor secundario para compatibilidad
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

    companion object {
        // Candidatos Alto Selva Alegre
        private val candidatoAsa1 = Candidato(
            "Enrique Salas Carreño",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/bGIvSmeb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )

        private val candidatoAsa2 = Candidato(
            "Mariano Otazu Yana",
            Partido.Frente_Popular_Agricola_del_Peru,
            "https://i.imgur.com/YERtdfNb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )

        private val candidatoAsa3 = Candidato(
            "Anibal Agustin Salas Flores",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/uwo1fqib.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )

        private val candidatoAsa4 = Candidato(
            "David Adolfo Barriga Miranda",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/3qkkpQwb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa5 = Candidato(
            "Domingo Suclle Aragon",
            Partido.Partido_Politico_Nacional_Peru_Libre,
            "https://i.imgur.com/2Tyv1TZb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa6 = Candidato(
            "Jose Luis Narro Ortiz",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/2aoZYnMb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa7 = Candidato(
            "Rusbel Gutierrez Canaza",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/51MbijKb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa8 = Candidato(
            "Rusbel Gutierrez Canaza",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/51MbijKb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa9 = Candidato(
            "Hector Jesus Del Carpio Cayo",
            Partido.Avanza_Pais_Partido_De_Integracion_Social,
            "https://i.imgur.com/ZqQY1avb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa10 = Candidato(
            "Alfredo Willy Benavente Godoy",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/N6jA3tkb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa11 = Candidato(
            "Daniel Ricardo Delgado Quilla",
            Partido.Accion_Popular,
            "https://i.imgur.com/D03KlDkb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa12 = Candidato(
            "Raul Lima Coasaca",
            Partido.Partido_Morado,
            "https://i.imgur.com/9EW9Kheb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa13 = Candidato(
            "Rafael Vicente Villa Moroco",
            Partido.Fe_En_El_Peru,
            "https://i.imgur.com/HAjWOpHb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa14 = Candidato(
            "Jesus Antonio Gamero Marquez",
            Partido.Alianza_Para_El_Progreso,
            "https://i.imgur.com/3mXapoAb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa15 = Candidato(
            "Juan Molina Patiño",
            Partido.Partido_Frente_De_La_Esperanza_2021,
            "https://i.imgur.com/kRvtZ8fb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa16 = Candidato(
            "Cristhian Guillen Rivera",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/WcDoK5fb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa17 = Candidato(
            "Silvestre Frisancho Ancasi",
            Partido.Movimiento_Regional_Del_Ajo_Justicia_Y_Orden,
            "https://i.imgur.com/0rzd1Bsb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )
        private val candidatoAsa18 = Candidato(
            "Pedro Santos Cornejo Mamani",
            Partido.Juntos_Por_El_Peru,
            "https://i.imgur.com/RAvPBg3b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.ALTO_SELVA_ALEGRE
        )


        // Candidatos Cayma
        private val candidatoCay1 = Candidato(
            "Miriam Janet Pacheco de Carpio",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/7dcNb2yb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay2 = Candidato(
            "Oswaldo Alvaro Muñiz Huillca",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/pZI6Z81b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay3 = Candidato(
            "Moises Jesus Chuctaya Huarca",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/UC0eienb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay4 = Candidato(
            "Juan Carlos Callacondo Velarde",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/WzoTKFbb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay5 = Candidato(
            "Reynaldo Paredes Casapia",
            Partido.Partido_Politico_Nacional_Peru_Libre,
            "https://i.imgur.com/lv1oIsrb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay6 = Candidato(
            "Jose Renato Carreon Echegaray",
            Partido.Arequipa_Unidos_Por_El_Gran_Cambio,
            "https://i.imgur.com/9cc0FU0b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay7 = Candidato(
            "Juan carlos Linares Cama",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/nL3YKpZb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay8 = Candidato(
            "Gino Hector Bravo Ardiles",
            Partido.Avanza_Pais_Partido_De_Integracion_Social,
            "https://i.imgur.com/Klt1vUib.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay9 = Candidato(
            "Dolores Virginia Rodriguez Acosta",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/pAOrGWMb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay10 = Candidato(
            "Rodolfo Aquepucho Hacha",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/zapcliHb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay11 = Candidato(
            "Rodolfo Isaias QUispe Taco",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/zapcliHb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay12 = Candidato(
            "Wenceslao Salomon Tarifa Sucari",
            Partido.Podemos_Peru,
            "https://i.imgur.com/uErqMxIb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay13 = Candidato(
            "Hector Arturo Pita Catalan",
            Partido.Fuerza_Popular,
            "https://i.imgur.com/Z5fzSNjb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay14 = Candidato(
            "Fortunato Muñuico Muñuico",
            Partido.Partido_Frente_De_La_Esperanza_2021,
            "https://i.imgur.com/UTANjSub.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay15 = Candidato(
            "Pavel Miguel Ibarra Quispe",
            Partido.Alianza_Para_El_Progreso,
            "https://i.imgur.com/dNWGCyvb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )
        private val candidatoCay16 = Candidato(
            "Victor Simon Garcia ALvarez",
            Partido.Juntos_Por_El_Peru,
            "https://i.imgur.com/zA6tMt9b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CAYMA
        )


        //Candidatos Cerro Colorado
        private val candidatoCeColor1 = Candidato(
            "Hugo Efrain Aguilar Gonzales",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/O8wR91Ab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor2 = Candidato(
            "Hugo Efrain Aguilar Gonzales",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/O8wR91Ab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor3 = Candidato(
            "Santos ALfaro Rojas",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/5PugSU6b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor4 = Candidato(
            "Miguel Eliseo Apaza Peña",
            Partido.Avanza_Pais_Partido_De_Integracion_Social,
            "https://i.imgur.com/QUrqlrvb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor5 = Candidato(
            "Manuel Enrique Vera Paredes",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/HAwLDNZb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor6 = Candidato(
            "Pedro Florencio HUamani ANcasi",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/zVv7HjVb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor7 = Candidato(
            "Walter Benjamin Diaz Perez",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/Rf6DDDab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor8 = Candidato(
            "Luis Elias Apaza Huaman",
            Partido.Arequipa_Unidos_Por_El_Gran_Cambio,
            "https://i.imgur.com/5jFsdv8b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor9 = Candidato(
            "Pascual Puma Ayma",
            Partido.Partido_Politico_Nacional_Peru_Libre,
            "https://i.imgur.com/GBQ7akgb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor10 = Candidato(
            "Hermes Nicolas OScco Polar",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/rXdswNib.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor11 = Candidato(
            "Jesus Heraclides Torres Gonzales",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/cs2W2l5b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor12 = Candidato(
            "Wilfredo Quispe Gutierrez",
            Partido.Fe_En_El_Peru,
            "https://i.imgur.com/UFG3lGPb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )
        private val candidatoCeColor13 = Candidato(
            "Nila Yesmit Apaza Mamani",
            Partido.Partido_Frente_De_La_Esperanza_2021,
            "https://i.imgur.com/wNIU5iUb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CERRO_COLORADO
        )


        // Candidatos Characato
        private val candidatochr1 = Candidato(
            "Maribel Efigenia Linares Luque",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/FQxlqArb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr2 = Candidato(
            "Teresa Moscoso Oscco",
            Partido.Frente_Popular_Agricola_del_Peru,
            "https://i.imgur.com/0kURfjKb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr3 = Candidato(
            "Alexander Hector Quise Vilca",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/CZrTTvbb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )

        private val candidatochr4 = Candidato(
            "Angel Anastacio Linares Portilla",
            Partido.Arequipa_Renace,
            "https://i.imgur.com/E5H4x5ab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr5 = Candidato(
            "Carlos Alberto Portilla Tejada",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/7Wh7okdb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr6 = Candidato(
            "Jorge Rony Portilla Portilla",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/ymoi3DCb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr7 = Candidato(
            "Percy Juan Herrera Morales",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/OKikZiWb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr8 = Candidato(
            "Donilly Willy Alvarez Arce",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/8DjUSa3b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr9 = Candidato(
            "Jesus Godofredo Aguillar Guillen",
            Partido.Partido_Politico_Nacional_Peru_Libre,
            "https://i.imgur.com/9RUR69fb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr10 = Candidato(
            "Luciano Mamani Maquera",
            Partido.Avanza_Pais_Partido_De_Integracion_Social,
            "https://i.imgur.com/plmw2upb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )
        private val candidatochr11 = Candidato(
            "Lucero Violeta Linares Cornejo",
            Partido.Movimiento_Regional_Del_Ajo_Justicia_Y_Orden,
            "https://i.imgur.com/BjRFeN4b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHARACATO
        )

        //Candidatos
        private val candidatochi1 = Candidato(
            "Piter Vilca Gallegos",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/EgYt3RHb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi2 = Candidato(
            "Gladys Ticona Flores",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/LofIZyYb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi3 = Candidato(
            "Yris Paola Calisaya Vilca",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/wsqEHB5b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi4 = Candidato(
            "Gregorio Angel Corrales Delgado",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/2ZakvTZb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi5 = Candidato(
            "Emiliano Vilca Cruz",
            Partido.Partido_Politico_Nacional_Peru_Libre,
            "https://i.imgur.com/3N4WyY5b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi6 = Candidato(
            "Remigio Bernardo Cahua Sahuanay",
            Partido.Partido_Frente_De_La_Esperanza_2021,
            "https://i.imgur.com/JLlEKxob.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )
        private val candidatochi7 = Candidato(
            "Moises Urbano Vizcarra Andamayo",
            Partido.Alianza_Para_El_Progreso,
            "https://i.imgur.com/UC0eienb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.CHIGUATA
        )

        //Candidatos Jacobo Hunter
        private val candidatohunt1 = Candidato(
            "Cecilia Imelda Fuentes Guerra",
            Partido.Avanza_Pais_Partido_De_Integracion_Social,
            "https://i.imgur.com/8njp1FIb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JACOBO_HUNTER
        )
        private val candidatohunt2 = Candidato(
            "Sergio Alfredo Barriga Rodriguez",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/toYFurSb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JACOBO_HUNTER
        )
        private val candidatohunt3 = Candidato(
            "Jummy Edgardo Vargas Espinoza",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/shu3L39b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JACOBO_HUNTER
        )
        private val candidatohunt4 = Candidato(
            "Reger Andia Romero",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/9QLVAlBb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JACOBO_HUNTER
        )
        private val candidatohunt5 = Candidato(
            "David Apaza Enriquez",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/hhcStwmb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JACOBO_HUNTER
        )

        //Candidatos Jose luis Bustamante y Rivero
        private val candidatojlb1 = Candidato(
            "Ronldo Pablo Ibañez Barreda",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/cLQOfneb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JOSE_LUIS_BUSTAMANTE_Y_RIVERO
        )
        private val candidatojlb2 = Candidato(
            "Guillermo Pablo Reinoso Barletti",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/xQTPlIMb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JOSE_LUIS_BUSTAMANTE_Y_RIVERO
        )
        private val candidatojlb3 = Candidato(
            "David Zapata Lovaton",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/tR2NkuJb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JOSE_LUIS_BUSTAMANTE_Y_RIVERO
        )
        private val candidatojlb4 = Candidato(
            "Cesar Mario Flores Ytusaca",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/GaBg3f9b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JOSE_LUIS_BUSTAMANTE_Y_RIVERO
        )
        private val candidatojlb5 = Candidato(
            "Carlo Ramiro Aliaga Nuñez",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/9v2lwWub.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.JOSE_LUIS_BUSTAMANTE_Y_RIVERO
        )

        //Candidatos Mariano Melgar
        private val candidatomm1 = Candidato(
            "Sergio Gonzales Apaza",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/FoaLu0cb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MARIANO_MELGAR
        )
        private val candidatomm2 = Candidato(
            "Jose Maquera Torres",
            Partido.Frente_Popular_Agricola_del_Peru,
            "https://i.imgur.com/PebXrv4b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MARIANO_MELGAR
        )
        private val candidatomm3 = Candidato(
            "Oscar Alfredo Ayala Arenas",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/Jmm1IcGb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MARIANO_MELGAR
        )
        private val candidatomm4 = Candidato(
            "Roberto Carlo Molina Diaz",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/jAwObDUb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MARIANO_MELGAR
        )

        //Candidatos Miraflores
        private val candidatomir1 = Candidato(
            "German Torres Chambi",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/T8NGpOKb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MIRAFLORES
        )
        private val candidatomir2 = Candidato(
            "Marco NatonioCenty Lopez",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/CCZz1FEb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MIRAFLORES
        )
        private val candidatomir3 = Candidato(
            "Jose Roberto Roque Quispe",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/6Utbg47b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MIRAFLORES
        )
        private val candidatomir4 = Candidato(
            "Pedro Ramiro Escobedo Aza",
            Partido.Renovacion_Popular,
            "https://i.imgur.com/EVAguhjb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MIRAFLORES
        )
        private val candidatomir5 = Candidato(
            "Luis Angel Sanxhez Ñaupa",
            Partido.Podemos_Peru,
            "https://i.imgur.com/laaOB3tb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.MIRAFLORES
        )

        //Candidatos Paucarpata
        private val candidatopau1 = Candidato(
            "Marco Antonio Anco Huarachi",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/kmia98Nb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.PAUCARPATA
        )
        private val candidatopau2 = Candidato(
            "Esteban Llacchua Segovia",
            Partido.Frente_Popular_Agricola_del_Peru,
            "https://i.imgur.com/Qb17HUFb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.PAUCARPATA
        )
        private val candidatopau3 = Candidato(
            "Angel David Condori",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/2lWpbsub.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.PAUCARPATA
        )
        private val candidatopau4 = Candidato(
            "Alvaro Florentino Rojas Chavez",
            Partido.Fe_En_El_Peru,
            "https://i.imgur.com/GFbUgf0b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.PAUCARPATA
        )
        private val candidatopau5 = Candidato(
            "Raul Gregorio Chara Chambi",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/yx2yGplb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.PAUCARPATA
        )

        //candidatos de
        private val candidatosab1 = Candidato(
            "Herless Ronald Diaz Perea",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/F7NKGSNb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SABANDIA
        )
        private val candidatosab2 = Candidato(
            "Santos Albertano Salinas Valencia",
            Partido.Arequipa_Renace,
            "https://i.imgur.com/932PmvQb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SABANDIA
        )
        private val candidatosab3 = Candidato(
            "Victor Raul Pauca Calcina",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/2uXSnR0b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SABANDIA
        )
        private val candidatosab4 = Candidato(
            "Leslie Noelia Stephany Castillo Rodriguez",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/Kqh2AFMb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SABANDIA
        )
        private val candidatosab5 = Candidato(
            "Johel Agapito Calderon Taco",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/j37jjPjb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SABANDIA
        )

        // Candidatos Sachaca
        private val candidatosach1 = Candidato(
            "Evaristo Florentino Calderon Nuñez",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/4nF2ArQb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SACHACA
        )
        private val candidatosach2 = Candidato(
            "Ivan Fortunato Fernandez Febres",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/K5uxw3ab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SACHACA
        )
        private val candidatosach3 = Candidato(
            "Miguel Angel Cuadros Paredes",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/WSAJkftb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SACHACA
        )
        private val candidatosach4 = Candidato(
            "Renzo ALonso Salas Herrera",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/FJ1xcSkb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SACHACA
        )
        private val candidatosach5 = Candidato(
            "Lissett Romina Diaz Valdez",
            Partido.Renovacion_Popular,
            "https://i.imgur.com/89g6ke6b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SACHACA
        )

        //Candidatos Socabaya
        private val candidatosoc1 = Candidato(
            "Zacarias Pfoccori Pumacallahue",
            Partido.Frente_Popular_Agricola_del_Peru,
            "https://i.imgur.com/A1MMPhqb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SOCABAYA
        )
        private val candidatosoc2 = Candidato(
            "Juan Carlos Ortiz Villalta",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/8uDsM6bb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SOCABAYA
        )
        private val candidatosoc3 = Candidato(
            "Glissolym Soledad Monataño Condori",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/oCkDEPcb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SOCABAYA
        )
        private val candidatosoc4 = Candidato(
            "Romulo Freddy Teran Trigoso Soto",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/9ErYg1Lb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SOCABAYA
        )
        private val candidatosoc5 = Candidato(
            "Pedro Jesus Fuentes Lopez",
            Partido.Fe_En_El_Peru,
            "https://i.imgur.com/LcglbRFb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.SOCABAYA
        )

        //Candidatos Tiabaya
        private val candidatotia1 = Candidato(
            "Julio Renato Marca Mamani",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/Y5hkZgKb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.TIABAYA
        )
        private val candidatotia2 = Candidato(
            "Nelson Godofredo Delgado Dueños",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/fQgg8f0b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.TIABAYA
        )
        private val candidatotia3 = Candidato(
            "Eduardo Mejia Vargas",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/qahOKSZb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.TIABAYA
        )
        private val candidatotia4 = Candidato(
            "Lizeth Maribel Mamani Corrales",
            Partido.Juntos_Por_El_Desarrollo_De_Arequipa,
            "https://i.imgur.com/vXnJuOAb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.TIABAYA
        )
        private val candidatotia5 = Candidato(
            "Rissy Paola Calderon Zeballos",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/vC7dscHb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.TIABAYA
        )

        //Candidatos Uchumayo
        private val candidatouchu1 = Candidato(
            "Alberto Guillermo Zevallos Torres",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/Q8odWnjb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.UCHUMAYO
        )
        private val candidatouchu2 = Candidato(
            "Jorge Luis Alvarez Briceño",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/UzLYcn2b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.UCHUMAYO
        )
        private val candidatouchu3 = Candidato(
            "Jorge Alfredo Tapia Neira",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/TlLY1aFb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.UCHUMAYO
        )
        private val candidatouchu4 = Candidato(
            "Gualberto Chilo Monroy",
            Partido.Renovacion_Popular,
            "https://i.imgur.com/2QyM6Pab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.UCHUMAYO
        )
        private val candidatouchu5 = Candidato(
            "Hardin Jose Abril Velarde",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/aXLf8Wab.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.UCHUMAYO
        )

        //Candidatos Ynahuara
        private val candidatoyana1 = Candidato(
            "Ernesto Jesus Montoya Mogrovejo",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/zi5ALEtb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YANAHUARA
        )
        private val candidatoyana2 = Candidato(
            "Sergio Javier Bolliger Marroquin",
            Partido.Arequipa_transformacion,
            "https://i.imgur.com/e0a23MRb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YANAHUARA
        )
        private val candidatoyana3 = Candidato(
            "Jose Augusto Arce Paredes",
            Partido.Partido_Democratico_Somos_Peru,
            "https://i.imgur.com/Sf4p5QIb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YANAHUARA
        )
        private val candidatoyana4 = Candidato(
            "Aldo Alonso Franco Salinas",
            Partido.Movimiento_Regional_Reveladora,
            "https://i.imgur.com/BhJBVWXb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YANAHUARA
        )
        private val candidatoyana5 = Candidato(
            "Pilar Teresa Vera Madariaga",
            Partido.Accion_Popular,
            "https://i.imgur.com/5Ti8EGMb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YANAHUARA
        )

        //Candidatos Yura
        private val candidatoyur1 = Candidato(
            "Mirtha Mavel Ruelas Casillas",
            Partido.Arequipa_Tradicion_Futuro,
            "https://i.imgur.com/aLqQnp4b.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YURA
        )
        private val candidatoyur2 = Candidato(
            "Maria Magdalena Llasaca Ala",
            Partido.Fuerza_Arequipenya,
            "https://i.imgur.com/TpQIoFSb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YURA
        )
        private val candidatoyur3 = Candidato(
            "Julio Jose Fuentes Barriga",
            Partido.Arequipa_Renace,
            "https://i.imgur.com/7R98Ezzb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YURA
        )
        private val candidatoyur4 = Candidato(
            "Ruben Manuel Pari Cañaza",
            Partido.Yo_Arequipa,
            "https://i.imgur.com/0Y5jNHPb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YURA
        )
        private val candidatoyur5 = Candidato(
            "Luis Javier Fuentes Salas",
            Partido.Movimineto_Regional_Arequipa_Avancemos,
            "https://i.imgur.com/9ebzc8Nb.jpg",
            "Mas informacion:  ipsum, dolor sit amet consectetur adipisicing elit. Cum nam ab quae impedit repudiandae, sunt pariatur facere amet " +
                    "obcaecati iusto repellat, officiis incidunt rerum nesciunt necessitatibus? Culpa voluptas autem excepturi!",
            Distrito.YURA
        )

        var candidatos = arrayListOf(
            candidatoAsa1,
            candidatoAsa2,
            candidatoAsa3,
            candidatoAsa4,
            candidatoAsa5,
            candidatoAsa6,
            candidatoAsa7,
            candidatoAsa8,
            candidatoAsa9,
            candidatoAsa10,
            candidatoAsa11,
            candidatoAsa12,
            candidatoAsa13,
            candidatoAsa14,
            candidatoAsa15,
            candidatoAsa16,
            candidatoAsa17,
            candidatoAsa18,
            //Candidatos Cayma
            candidatoCay1,
            candidatoCay2,
            candidatoCay3,
            candidatoCay4,
            candidatoCay5,
            candidatoCay6,
            candidatoCay7,
            candidatoCay8,
            candidatoCay9,
            candidatoCay10,
            candidatoCay11,
            candidatoCay12,
            candidatoCay13,
            candidatoCay14,
            candidatoCay15,
            candidatoCay16,
            //Candidatos Cerro Colorado
            candidatoCeColor1,
            candidatoCeColor2,
            candidatoCeColor3,
            candidatoCeColor4,
            candidatoCeColor5,
            candidatoCeColor6,
            candidatoCeColor7,
            candidatoCeColor8,
            candidatoCeColor9,
            candidatoCeColor10,
            candidatoCeColor11,
            candidatoCeColor12,
            candidatoCeColor13,
            //Candidatos Characato
            candidatochr1,
            candidatochr2,
            candidatochr3,
            candidatochr4,
            candidatochr5,
            candidatochr6,
            candidatochr7,
            candidatochr8,
            candidatochr9,
            candidatochr10,
            candidatochr11,
            //candidatos chiguata
            candidatochi1,
            candidatochi2,
            candidatochi3,
            candidatochi4,
            candidatochi5,
            candidatochi6,
            candidatochi7,
            //Candidatos Javobo HUnter
            candidatohunt1,
            candidatohunt2,
            candidatohunt3,
            candidatohunt4,
            candidatohunt5,
            //Candidatos Jose Luis Bustamente y Rivero
            candidatojlb1,
            candidatojlb2,
            candidatojlb3,
            candidatojlb4,
            candidatojlb5,
            //Candiadtos Mariano Melgar
            candidatomm1,
            candidatomm2,
            candidatomm3,
            candidatomm4,
            //Candidatos Miraflores
            candidatomir1,
            candidatomir2,
            candidatomir3,
            candidatomir4,
            candidatomir5,
            //Candidatos Paucarpata
            candidatopau1,
            candidatopau2,
            candidatopau3,
            candidatopau4,
            candidatopau5,
            //Candidatos Sabandia
            candidatosab1,
            candidatosab2,
            candidatosab3,
            candidatosab4,
            candidatosab5,
            //Candidatos Sachaca
            candidatosach1,
            candidatosach2,
            candidatosach3,
            candidatosach4,
            candidatosach5,
            //Candidatos Socabaya
            candidatosoc1,
            candidatosoc2,
            candidatosoc3,
            candidatosoc4,
            candidatosoc5,

            //Candidatos Tiabaya
            candidatotia1,
            candidatotia2,
            candidatotia3,
            candidatotia4,
            candidatotia5,

            //Candidatos Uchumayo
            candidatouchu1,
            candidatouchu2,
            candidatouchu3,
            candidatouchu4,
            candidatouchu5,
            //Candidatos Ynahuara
            candidatoyana1,
            candidatoyana2,
            candidatoyana3,
            candidatoyana4,
            candidatoyana5,
            //Candidatos Yura
            candidatoyur1,
            candidatoyur2,
            candidatoyur3,
            candidatoyur3,
            candidatoyur5,
        )
    }
}

