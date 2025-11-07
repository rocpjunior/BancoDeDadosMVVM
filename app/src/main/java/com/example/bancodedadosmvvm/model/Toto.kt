package com.example.bancodedadosmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity

data class Toto (
    @PrimaryKey(autoGenerate = true) //Marco que o próximo dado é identificado
    val id: Int = 0,
    val nome: String,
    val img: String,
    val preco: Double,
    val completou: Boolean = false
)