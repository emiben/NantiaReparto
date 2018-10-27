package com.nantia.repartonantia.venta;

import com.nantia.repartonantia.listadeprecios.ProductoLista;

import java.util.List;

public interface ListaProductoVentaView {
    void onSetProgressBarVisibility(int visibility);
    void showStockError(float cant);
    void loadData(List<ProductoLista> prodsLista);
    void setListeners();
    void updateCantidadCarro(int cant);
    void showStockNoAsociadoError();
    void showClienteSinListaPrecioError();
    void finishActivity();
}
