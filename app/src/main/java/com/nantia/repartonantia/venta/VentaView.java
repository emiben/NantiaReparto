package com.nantia.repartonantia.venta;

import com.google.android.gms.maps.model.LatLng;

public interface VentaView {
    void onSetProgressBarVisibility(int visibility);
    void updateData(Venta venta);
    boolean isVendedor1Checked();
    void finishActivities();
    LatLng getUbicacionVeiculo();
}
