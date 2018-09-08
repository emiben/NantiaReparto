package com.nantia.repartonantia.reparto;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.map.ClienteMapaFragment;

public class RepartoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparto);
        RepartoMapFragment clienteMapaFragment = new RepartoMapFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.reparto_layout, clienteMapaFragment)
                .commit();
    }
}
