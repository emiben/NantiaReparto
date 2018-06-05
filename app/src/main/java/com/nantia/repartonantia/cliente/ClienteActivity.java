package com.nantia.repartonantia.cliente;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ClienteListaFragment clienteListaFragment = new ClienteListaFragment();
        fragmentTransaction.add(R.id.cliente_lista_layout, clienteListaFragment).commit();
    }
}
