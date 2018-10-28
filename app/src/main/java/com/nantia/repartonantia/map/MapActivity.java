package com.nantia.repartonantia.map;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.data.DataHolder;

import java.io.Serializable;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ClienteListaMapFragment clienteListaMapFragment = new ClienteListaMapFragment();
        //Consulto si vengo del reparto
//        if(this.getIntent().getBundleExtra(KEY_CLIENTE_LISTA) != null){
        if(this.getIntent().getBooleanExtra(KEY_REPARTO, false)){
            Bundle b = new Bundle();
            b.putBoolean(KEY_REPARTO, true);
//            b.putSerializable(KEY_CLIENTE_LISTA,
//                    this.getIntent().getBundleExtra(KEY_CLIENTE_LISTA).getSerializable(KEY_CLIENTE_LISTA));
            clienteListaMapFragment.setArguments(b);
        }
//        else if (DataHolder.getClientes() != null){
//            Bundle b = new Bundle();
//            b.putSerializable(KEY_CLIENTE_LISTA, (Serializable) DataHolder.getClientes());
//            clienteListaMapFragment.setArguments(b);
//        }
        fragmentTransaction.add(R.id.map_layout, clienteListaMapFragment).commit();
    }
}
