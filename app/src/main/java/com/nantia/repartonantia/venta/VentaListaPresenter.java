package com.nantia.repartonantia.venta;

import android.view.View;

import com.nantia.repartonantia.data.DataHolder;

public class VentaListaPresenter {
    private final String TAG = VentaListaPresenter.class.getName();
    private VentaListaView view;

    public VentaListaPresenter(VentaListaView view) {
        this.view = view;
    }

    public void getVentas() {
        view.onSetProgressBarVisibility(View.VISIBLE);
        if(DataHolder.getVentas() != null){
            view.setVentasInfo(DataHolder.getVentas());
        }
        view.onSetProgressBarVisibility(View.GONE);
    }
}
