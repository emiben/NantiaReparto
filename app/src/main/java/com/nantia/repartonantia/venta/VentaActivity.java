package com.nantia.repartonantia.venta;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.Cliente;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;

public class VentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        Cliente cliente = (Cliente) this.getIntent().getExtras().getSerializable(KEY_CLIENTE);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE, cliente);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListaProductoVentaFragment fragment = new ListaProductoVentaFragment();
        fragment.setArguments(b);
        fragmentTransaction.add(R.id.venta_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
