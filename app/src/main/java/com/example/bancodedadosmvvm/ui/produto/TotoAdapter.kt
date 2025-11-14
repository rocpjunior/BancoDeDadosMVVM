package com.example.bancodedadosmvvm.ui.produto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bancodedadosmvvm.R
import com.example.bancodedadosmvvm.model.Toto

class TotoAdapter (
    private val itemListaToto: MutableList<Toto>,
    private val listando: AdaptandoLista,
    private val contexto: Context
): RecyclerView.Adapter<TotoAdapter.ViewHolder>(){

    override fun getItemCount() = itemListaToto.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_a_ser_listado))
    }

    override fun onBindViewHolder(
        holder: TotoAdapter.ViewHolder,
        position: Int) {
        holder.bindViewHolder(itemListaToto[position])
    }

    fun updateList(itemLista: MutableList<Toto>){
        this.itemListaToto.clear()
        this.itemListaToto.addAll(itemLista)
        notifyDataSetChanged()
    }

    interface AdaptandoLista{
        fun marcarComoConcluido(toto: Toto)
        fun deletarItem(toto: Toto)
    }

    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                private lateinit var item: Toto
            fun bindViewHolder(item: Toto){
            this.item = item
            val txtTarefa = itemView.findViewById<TextView>(R.id.txtTarefa)
            txtTarefa.text = item.nome
            configurarTotosCompletos(item)
            val img = itemView.findViewById<ImageView>(R.id.imgCompletou)
            img.setOnClickListener{listando.marcarComoConcluido(item)}
                //Desenvoler um botão de remover

                val elemento = itemView.findViewById<LinearLayout>(R.id.item_lista_controlador)
                elemento.setOnClickListener {
                    listando.deletarItem(item)
                    return@setOnClickListener
                }
            }

        private fun configurarTotosCompletos(toto: Toto){
            if(toto.completou){
                DrawableCompat.setTint(
                    DrawableCompat.wrap(itemView.
                    findViewById<ImageView>(R.id.imgCompletou).drawable),
                    ContextCompat.getColor(contexto, R.color.black)
                )
            }
            else{
                DrawableCompat.setTint(
                    DrawableCompat.wrap(itemView.
                    findViewById<ImageView>(R.id.imgCompletou).drawable),
                    ContextCompat.getColor(contexto, R.color.white)
                )
            }
        }
    }
}
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}