package com.nantia.repartonantia.reparto;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.data.DataHolder;

import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

public class RepartoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparto);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(DataHolder.getReparto() != null &&
                !DataHolder.getReparto().getEstado().equals(RepartoEstado.CREADO.name())){
            Bundle b = new Bundle();
            b.putSerializable(KEY_REPARTO, DataHolder.getReparto());
            RepartoFragment repartoFragment = new RepartoFragment();
            repartoFragment.setArguments(b);
            fragmentTransaction.replace(R.id.reparto_layout, repartoFragment).commit();
        }else {
            ListaRepartoFragment fragment = new ListaRepartoFragment();
            fragmentTransaction.add(R.id.reparto_layout, fragment).commit();
        }

    }
}
