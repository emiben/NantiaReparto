package com.nantia.repartonantia.venta;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.Cliente;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;
import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA_LISTA;

public class VentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(this.getIntent().getBooleanExtra(KEY_VENTA_LISTA, false)){
            VentaListaFragment fragment = new VentaListaFragment();
            fragmentTransaction.add(R.id.venta_layout, fragment).commit();
        }else{
            Cliente cliente = (Cliente) this.getIntent().getBundleExtra(KEY_CLIENTE).getSerializable(KEY_CLIENTE);
            Bundle b = new Bundle();
            b.putSerializable(KEY_CLIENTE, cliente);
            ListaProductoVentaFragment fragment = new ListaProductoVentaFragment();
            fragment.setArguments(b);
            fragmentTransaction.add(R.id.venta_layout, fragment).commit();
        }
    }
}
