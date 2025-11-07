package com.example.bancodedadosmvvm.source

import androidx.room.*
import com.example.bancodedadosmvvm.model.Toto
@Dao
interface TotoDao {
    @Insert
    suspend fun addItem(toto: Toto)

    @Query("SELECT * FROM Toto ORDER BY completou, id")
    suspend fun obterElementos(): List<Toto>

    @Query("UPDATE Toto SET completou = 1 WHERE id = :id")
    suspend fun marcarComoConcluido(id: Int)

    @Delete
    suspend fun delItem(toto: Toto)
}