package com.example.bancodedadosmvvm.ui.produto

import androidx.lifecycle.*
import com.example.bancodedadosmvvm.model.Toto
import com.example.bancodedadosmvvm.repository.TotoRepository
import kotlinx.coroutines.launch

class ProdutoViewModel(private val totoRepository: TotoRepository): ViewModel() {
    private val todos = MutableLiveData<List<Toto>>()
    fun observaTodos(): LiveData<List<Toto>> = todos

    private val erros = MutableLiveData<String>()
    fun observaErros(): LiveData<String> = erros

    fun InserirItem(nome: String, preco: Double, img: String){
        viewModelScope.launch {

            try {
                val resultado = totoRepository.insercaoItem(nome,preco,img)
                todos.value = resultado
            }catch (e: Exception){
                 erros.value = e.message
            }
        }
    }

    fun obterTodos() {
        viewModelScope.launch {
            try {
                todos.value = totoRepository.obterTodos()
            }catch (e: Exception){
                erros.value = e.message
            }
        }
    }

    fun deletarItem(toto: Toto){
        viewModelScope.launch {
            try {
                todos.value = totoRepository.deletarItem(toto)
            }catch (e: Exception){
                erros.value = e.message
            }
        }
    }

    fun atualizarItem(toto: Toto){
        viewModelScope.launch {
            try {
                todos.value = totoRepository.marcacaoConcluida(toto)
            }catch (e: Exception){
                erros.value = e.message
            }
        }
    }
}