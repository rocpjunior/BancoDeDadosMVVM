package com.example.bancodedadosmvvm.source
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bancodedadosmvvm.model.Toto

@Database(entities = [Toto::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun totoDao(): TotoDao
    companion object {
        @Volatile
        private var instancia: AppDatabase? = null
        private val BLOQUEIO = Any()

        operator fun invoke(context: Context)
        = instancia ?: synchronized(BLOQUEIO) {
            instancia ?: construirDatabase(context).also{
                instancia = it
            }
        }
        private fun construirDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Toto.db"
            ).build()
    }
}