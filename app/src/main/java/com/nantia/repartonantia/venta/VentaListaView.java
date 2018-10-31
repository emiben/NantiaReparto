package com.nantia.repartonantia.venta;

import java.util.List;

public interface VentaListaView {
    void onSetProgressBarVisibility(int visibility);
    void setVentasInfo(List<Venta> ventas);
    void addListeners();
}
