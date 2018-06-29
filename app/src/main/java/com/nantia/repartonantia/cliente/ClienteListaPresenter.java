package com.nantia.repartonantia.cliente;

import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 28/5/2018.
 */

public class ClienteListaPresenter {
    private final String TAG = ClienteListaPresenter.class.getName();
    private ClienteListaView clienteListaView;

    public ClienteListaPresenter(ClienteListaView clienteListaView) {
        this.clienteListaView = clienteListaView;
    }

    public void getClientes() {
        clienteListaView.onSetProgressBarVisibility(View.VISIBLE);

        ClienteService clienteService = RetrofitClientInstance.getRetrofitInstance().create(ClienteService.class);
        Call<ArrayList<Cliente>> call = clienteService.getClientes();
        call.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                clienteListaView.setClienteInfo(response.body());
                clienteListaView.addListeners();
                clienteListaView.onSetProgressBarVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                clienteListaView.onSetProgressBarVisibility(View.GONE);
                Log.e(TAG, t.getMessage());
                clienteListaView.showError(t.getMessage());
            }
        });

//        ArrayList<Cliente> clientes = new ArrayList<>();
//        ArrayList<EnvaseEnPrestamo> envasesEnPrestamo = new ArrayList<>();
//        envasesEnPrestamo.add(new EnvaseEnPrestamo(new Envase(1, "Sifon"), 4));
//        envasesEnPrestamo.add(new EnvaseEnPrestamo(new Envase(2, "Damajuana"), 2));
//        envasesEnPrestamo.add(new EnvaseEnPrestamo(new Envase(3, "Bidon 20L"), 3));
//        for(int i=0; i < 50; i++){
//            Date date = Calendar.getInstance().getTime();
//            Direccion dir = new Direccion(i, "Dir"+i,i,i,i,"Dir"+i,"Dir"+i,"Dir"+i, true, true);
//            Cliente cli = new Cliente(i,dir, TipoDocumento.CI, String.valueOf(i),"Cli"+i, "Cli"+i,i,
//                    envasesEnPrestamo, date, date,i, "Cli"+i, i, "Cli"+i, true);
//            clientes.add(cli);
//        }
//        clienteListaView.setClienteInfo(clientes);
//        clienteListaView.addListeners();
//        clienteListaView.onSetProgressBarVisibility(View.GONE);
    }
}
