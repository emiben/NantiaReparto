package com.nantia.repartonantia.producto;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

public class ProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ProductoListaFragment productoListaFragment = new ProductoListaFragment();
        fragmentTransaction.add(R.id.producto_lista_layout, productoListaFragment).commit();

    }


}
