package com.example.bancodedadosmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancodedadosmvvm.ui.produto.ProdutoFragment

class TelaPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProdutoFragment.newInstance())
                .commitNow()
        }
    }
}