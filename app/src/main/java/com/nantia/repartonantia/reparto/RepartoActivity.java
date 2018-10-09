package com.nantia.repartonantia.reparto;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

public class RepartoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparto);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListaRepartoFragment fragment = new ListaRepartoFragment();
        fragmentTransaction.add(R.id.reparto_layout, fragment).commit();
    }
}
