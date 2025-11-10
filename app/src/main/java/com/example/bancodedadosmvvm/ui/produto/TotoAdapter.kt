package com.example.bancodedadosmvvm.ui.produto

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bancodedadosmvvm.R
import com.example.bancodedadosmvvm.model.Toto

class TotoAdapter (
    private val itemLista: MutableList<Toto>,
    private val listando: AdapterListener,
    private val contexto: Context
)