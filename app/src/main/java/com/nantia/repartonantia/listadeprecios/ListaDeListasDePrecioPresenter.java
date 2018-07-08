package com.nantia.repartonantia.listadeprecios;

import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 6/7/2018.
 */

public class ListaDeListasDePrecioPresenter {
    private final String TAG = "ListaDeListasPresenter";
    private ListaDeListaDePreciosView view;

    public ListaDeListasDePrecioPresenter(ListaDeListaDePreciosView view) {
        this.view = view;
    }

    public void getListasDePrecios(){
        view.onSetProgressBarVisibility(View.VISIBLE);
        if(DataHolder.getListasDePrecios() != null && DataHolder.getListasDePrecios().size() > 0){
            view.setListasDePrecioInfo(DataHolder.getListasDePrecios());
            view.addListeners();
            view.onSetProgressBarVisibility(View.GONE);
        }else {
            ListaDePreciosService listaDePreciosService =
                    RetrofitClientInstance.getRetrofitInstance().create(ListaDePreciosService.class);
            Call<ArrayList<ListaDePrecio>> call = listaDePreciosService.getListasDePrecios();
            call.enqueue(new Callback<ArrayList<ListaDePrecio>>() {
                @Override
                public void onResponse(Call<ArrayList<ListaDePrecio>> call, Response<ArrayList<ListaDePrecio>> response) {
                    if(response.code() < 300 && response.body() != null){
                        DataHolder.setListasDePrecios(response.body());
                        view.setListasDePrecioInfo(response.body());
                        view.addListeners();
                    }else{
                        view.showError(R.string.lista_precios_no_lista);
                    }
                    view.onSetProgressBarVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ArrayList<ListaDePrecio>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    view.onSetProgressBarVisibility(View.GONE);
                    view.showError(t.getMessage());
                }
            });
        }
    }
}
