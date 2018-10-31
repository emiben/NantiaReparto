package com.nantia.repartonantia.cliente;

import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.data.DataHolder;
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

        if(DataHolder.getClientes() == null || DataHolder.getClientes().size() == 0){
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
        } else{
            clienteListaView.setClienteInfo(DataHolder.getClientes());
            clienteListaView.addListeners();
            clienteListaView.onSetProgressBarVisibility(View.GONE);
        }

    }
}
