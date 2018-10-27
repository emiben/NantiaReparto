package com.nantia.repartonantia.usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.data.DataHolder;

public class VendedoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores);

        loadViewObjects();
    }

    private void loadViewObjects(){
        if(DataHolder.getReparto() != null){
            if(DataHolder.getReparto().getVendedor1() != null){
                ((TextView)findViewById(R.id.vendedor1_nombre_tv))
                        .setText(DataHolder.getReparto().getVendedor1().getNombreCompleto());
                ((TextView)findViewById(R.id.vendedor1_caja_tv))
                        .setText(String.valueOf(DataHolder.getReparto().getVendedor1().getSaldoCaja()));
            }

            if(DataHolder.getReparto().getVendedor2() != null){
                ((TextView)findViewById(R.id.vendedor2_nombre_tv))
                        .setText(DataHolder.getReparto().getVendedor2().getNombreCompleto());
                ((TextView)findViewById(R.id.vendedor2_caja_tv))
                        .setText(String.valueOf(DataHolder.getReparto().getVendedor2().getSaldoCaja()));
            }
        }
    }
}
