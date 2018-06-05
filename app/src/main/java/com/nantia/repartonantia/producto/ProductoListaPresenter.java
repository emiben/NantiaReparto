package com.nantia.repartonantia.producto;

import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 21/5/2018.
 */

public class ProductoListaPresenter {
    private final String TAG = ProductoListaPresenter.class.getName();
    private ProductoListaView productoListaView;

    public ProductoListaPresenter(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
    }

    public void getProductos(){
        final ArrayList<Producto> productos = new ArrayList<>();
        productoListaView.onSetProgressBarVisibility(View.VISIBLE);
        ProductoService productoService = RetrofitClientInstance.getRetrofitInstance().create(ProductoService.class);

        Call<ArrayList<Producto>> call = productoService.getProductos();
        call.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {
                productoListaView.setProductosInfo(response.body());
                productoListaView.addListeners();
                productoListaView.onSetProgressBarVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
                productoListaView.onSetProgressBarVisibility(View.GONE);
                Log.e(TAG, t.getMessage());
                productoListaView.showError(t.getMessage());
            }
        });
    }
}
