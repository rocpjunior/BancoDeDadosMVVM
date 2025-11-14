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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProdutoFragment : Fragment(), TotoAdapter.AdaptandoLista {

    companion object {
        fun newInstance() = ProdutoFragment()
    }

    private lateinit var mainViewModel: ProdutoViewModel
    private lateinit var adaptando: TotoAdapter
    private lateinit var totoRepositoryImplementacao: TotoRepository

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
        totoRepositoryImplementacao =
            TotoRepositoryImplementacao(
            bancodedados.totoDao()
        )
        val factory = ViewModelFactory(totoRepositoryImplementacao)
        mainViewModel = ViewModelProvider(this, factory).get(ProdutoViewModel::class.java)

        val fabADD = view?.findViewById<FloatingActionButton>(R.id.fabAdd)
        configLista()
        observar()
        fabADD?.setOnClickListener { abrirNovoElemento() }
    }

    private fun iniciarBanco(){
        bancodedados = context.let {
            AppDatabase.invoke(it!!)
        }
    }

    private fun configLista(){
        adaptando = TotoAdapter(mutableListOf(), this, requireContext())
        val recyclerViewLista = view?.findViewById<RecyclerView>(R.id.rvLista)
        recyclerViewLista?.layoutManager = LinearLayoutManager(context)
        recyclerViewLista?.adapter = adaptando
    }

    private fun observar(){
        mainViewModel.obterTodos()
        mainViewModel.observaTodos().observe(viewLifecycleOwner, Observer{
            adaptando.updateList(it as MutableList<Toto>)
        })
        mainViewModel.observaErros().observe(viewLifecycleOwner, Observer{
            if(it != null){
                val txtErro = view?.findViewById<TextView>(R.id.txtErro)
                txtErro?.visibility = VISIBLE
                val recyclerView = view?.findViewById<RecyclerView>(R.id.rvLista)
                recyclerView?.visibility = GONE
                val fabADD = view?.findViewById<FloatingActionButton>(R.id.fabAdd)
                fabADD?.visibility = GONE
            }
        })
    }

    private fun abrirDialogo(toto: Toto){
        val dialogo = AlertDialog.Builder(requireContext())
        dialogo.setTitle("Remover elemento")
        dialogo.setMessage("Você quer remover esse item?")
        dialogo.setPositiveButton("Deletar"){_,_ -> mainViewModel.deletarItem(toto)}
        dialogo.setNegativeButton("Cancelar"){_,_ -> null}
        dialogo.show()
    }

    private fun abrirNovoElemento(){
        val dialogo = AlertDialog.Builder(requireContext())
        dialogo.setTitle("Adicionar novo produto")

        val novoLayout = LinearLayout(context)
        novoLayout.orientation = LinearLayout.VERTICAL
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(32, 24, 32, 0)

        val nome = EditText(context)
        nome.hint = "Digite o nome do produto"
        nome.layoutParams = lp
        novoLayout.addView(nome)

        val imagem = EditText(context)
        imagem.hint = "Digite o link da imagem"
        imagem.layoutParams = lp
        novoLayout.addView(imagem)

        val preco = EditText(context)
        preco.hint = "Digite o preço do produto"
        preco.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        preco.layoutParams = lp
        novoLayout.addView(preco)

        dialogo.setView(novoLayout)

        dialogo.setPositiveButton("Salvar"){_,_ ->
            val nomeProduto = nome.text.toString()
            val imagemProduto = imagem.text.toString()
            val precoProduto = preco.text.toString().toDoubleOrNull() ?: 0.0
            mainViewModel.InserirItem(nomeProduto, precoProduto, imagemProduto)
        }
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }

    override fun marcarComoConcluido(toto: Toto) {
        mainViewModel.atualizarItem(toto)
    }

    override fun deletarItem(toto: Toto) {
        abrirDialogo(toto)
    }
}