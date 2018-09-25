package com.nantia.repartonantia.venta;

public interface VentaView {
    void onSetProgressBarVisibility(int visibility);
    void updateData(Venta venta);
    boolean isVendedor1Checked();
}
