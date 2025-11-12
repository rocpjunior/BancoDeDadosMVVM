package com.example.bancodedadosmvvm.ui.produto

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bancodedadosmvvm.R
import com.example.bancodedadosmvvm.ViewModelFactory
import com.example.bancodedadosmvvm.model.Toto
import com.example.bancodedadosmvvm.repository.TotoRepository
import com.example.bancodedadosmvvm.repository.TotoRepositoryImplementacao
import  com.example.bancodedadosmvvm.source.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.app.AlertDialog
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProdutoFragment : Fragment(), TotoAdapter.AdaptandoLista {

    companion object {
        fun newInstance() = ProdutoFragment()
    }

    private lateinit var mainViewModel: ProdutoViewModel
    private lateinit var adaptando: TotoAdapter
    private lateinit var totoRepository: TotoRepository

    lateinit var bancodedados: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_produto, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iniciarBanco()
        totoRepositoryImpl =
            TotoRepositoryImplementacao(
            bancodedados.totoDao()
        )
        val factory = ViewModelFactory(totoRepositoryImplementacao)
        mainViewModel = ViewModelProvider(this, factory).get(ProdutoViewModel::class.java)
    }

}