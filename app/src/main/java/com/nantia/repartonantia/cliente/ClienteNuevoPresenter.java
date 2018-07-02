package com.nantia.repartonantia.cliente;

import android.opengl.Visibility;
import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.EnvaseService;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 29/5/2018.
 */

public class ClienteNuevoPresenter {
    private final String TAG = ClienteNuevoPresenter.class.getName();
    private ClienteNuevoView view;

    public ClienteNuevoPresenter(ClienteNuevoView view) {
        this.view = view;
    }

    public void navigateToClienteMapa(){

    }

    public void getEnvases(){
        view.onSetProgressBarVisibility(View.VISIBLE);
        if (DataHolder.getEnvases() != null){
            view.setEnvases(DataHolder.getEnvases());
            view.onSetProgressBarVisibility(View.GONE);
        }else {
            EnvaseService envaseService = RetrofitClientInstance.getRetrofitInstance().create(EnvaseService.class);
            Call<ArrayList<Envase>> call = envaseService.getEnvases();
            call.enqueue(new Callback<ArrayList<Envase>>() {
                @Override
                public void onResponse(Call<ArrayList<Envase>> call, Response<ArrayList<Envase>> response) {
                    DataHolder.setEnvases(response.body());
                    view.setEnvases(response.body());
                    view.onSetProgressBarVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ArrayList<Envase>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    view.onSetProgressBarVisibility(View.GONE);
                    view.showError(t.getMessage());
                }
            });
        }
    }

    public void saveCliente(final Cliente cliente){
        view.onSetProgressBarVisibility(View.VISIBLE);
        ClienteService clienteService = RetrofitClientInstance.getRetrofitInstance().create(ClienteService.class);
        Call<Cliente> call = clienteService.saveCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                //TODO revisar se el contains se maneja por el id
                if(DataHolder.getClientes().contains(response.body())){
                    DataHolder.getClientes().remove(response.body());
                }
                DataHolder.getClientes().add(response.body());
                view.onSetProgressBarVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                view.onSetProgressBarVisibility(View.GONE);
                view.showError(t.getMessage());
            }
        });
    }

    public void updateCliente(final Cliente cliente){
        view.onSetProgressBarVisibility(View.VISIBLE);
        ClienteService clienteService = RetrofitClientInstance.getRetrofitInstance().create(ClienteService.class);
        Call<Cliente> call = clienteService.updateCliente(cliente.getId(), cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                //TODO revisar se el contains se maneja por el id
                if(DataHolder.getClientes().contains(response.body())){
                    DataHolder.getClientes().remove(response.body());
                }
                DataHolder.getClientes().add(response.body());
                view.onSetProgressBarVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                view.onSetProgressBarVisibility(View.GONE);
                view.showError(t.getMessage());
            }
        });
    }
}
