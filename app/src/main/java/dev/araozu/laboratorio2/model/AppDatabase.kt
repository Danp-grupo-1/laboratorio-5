package dev.araozu.laboratorio2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Partido::class, Candidato::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partidoDao(): PartidoDao
    abstract fun candidatoDao(): CandidatoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                populate(instance)
                instance
            }
        }

        // Crea los partidos iniciales
        private fun populate(db: AppDatabase) {
            var partidoDao = db.partidoDao()
            partidos.forEach { partido ->
                partidoDao.insertAll(partido)
            }
        }
    }

}
