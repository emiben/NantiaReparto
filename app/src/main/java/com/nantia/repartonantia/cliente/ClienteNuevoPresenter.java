package com.nantia.repartonantia.cliente;

import android.util.Log;

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

        //TODO: Agregar el spinner

        if (DataHolder.getEnvases() != null){
            view.setEnvases(DataHolder.getEnvases());
        }else {
            EnvaseService envaseService = RetrofitClientInstance.getRetrofitInstance().create(EnvaseService.class);
            Call<ArrayList<Envase>> call = envaseService.getEnvases();
            call.enqueue(new Callback<ArrayList<Envase>>() {
                @Override
                public void onResponse(Call<ArrayList<Envase>> call, Response<ArrayList<Envase>> response) {
                    DataHolder.setEnvases(response.body());
                    view.setEnvases(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<Envase>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
}
