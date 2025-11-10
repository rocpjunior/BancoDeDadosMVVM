package com.example.bancodedadosmvvm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancodedadosmvvm.repository.TotoRepository

class ViewModelFactory (private val totoRepository: TotoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TotoRepository::class.java).newInstance(totoRepository)
    }
}