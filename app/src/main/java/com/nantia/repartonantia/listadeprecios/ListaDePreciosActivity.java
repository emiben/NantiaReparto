package com.nantia.repartonantia.listadeprecios;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

public class ListaDePreciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_precios);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListaDeListasDePrecioFragment listaDeListaDePrecioFragment = new ListaDeListasDePrecioFragment();
        fragmentTransaction.add(R.id.lista_precios_layout, listaDeListaDePrecioFragment)
                .commit();

    }
}
