package com.nantia.repartonantia.cliente;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;
import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        if(this.getIntent().getBundleExtra(KEY_CLIENTE) != null){
            Cliente cliente = (Cliente) this.getIntent().getBundleExtra(KEY_CLIENTE).getSerializable(KEY_CLIENTE);
            Bundle b = new Bundle();
            b.putSerializable(KEY_CLIENTE, cliente);
            ClienteFragment clienteFragmentf = new ClienteFragment();
            clienteFragmentf.setArguments(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.cliente_lista_layout, clienteFragmentf)
                    .commit();
        }else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            ClienteListaFragment clienteListaFragment = new ClienteListaFragment();

            //Cuando vengo de reparto le paso los clientes solo de reparto
            if(this.getIntent().getBundleExtra(KEY_CLIENTE_LISTA) != null &&
                    this.getIntent().getBundleExtra(KEY_CLIENTE_LISTA).getSerializable(KEY_CLIENTE_LISTA) != null){
                Bundle b = new Bundle();
                b.putSerializable(KEY_CLIENTE_LISTA,
                        this.getIntent().getBundleExtra(KEY_CLIENTE_LISTA).getSerializable(KEY_CLIENTE_LISTA));
                clienteListaFragment.setArguments(b);
            }
            fragmentTransaction.add(R.id.cliente_lista_layout, clienteListaFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        final Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.cliente_lista_layout);
        if (currentFragment != null && !(currentFragment instanceof ClienteListaFragment)) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
