package com.example.bancodedadosmvvm.repository

import com.example.bancodedadosmvvm.model.Toto
import com.example.bancodedadosmvvm.source.TotoDao

interface TotoRepository {
    suspend fun insercaoItem(nome: String, preco: Double, img: String): List<Toto>
    suspend fun obterTodos(): List<Toto>
    suspend fun marcacaoConcluida(toto: Toto): List<Toto>
    suspend fun deletarItem(toto: Toto): List<Toto>
}

class TotoRepositoryImplementacao(private val totoDao: TotoDao) : TotoRepository{
    override suspend fun insercaoItem(nome: String,
                                      preco: Double,
                                      img: String): List<Toto> {
        val toto = Toto(nome = nome, preco = preco, img = img)
        totoDao.addItem(toto)
        return obterTodos()
    }

    override suspend fun obterTodos() = totoDao.obterElementos()

    override suspend fun marcacaoConcluida(toto: Toto): List<Toto> {
        totoDao.marcarComoConcluido(toto.id)
        return obterTodos()
    }

    override suspend fun deletarItem(toto: Toto): List<Toto> {
        totoDao.delItem(toto)
        return obterTodos()
    }
}