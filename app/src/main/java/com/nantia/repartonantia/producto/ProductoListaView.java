package com.nantia.repartonantia.producto;

import java.util.ArrayList;

/**
 * Created by Emi on 21/5/2018.
 */

public interface ProductoListaView {
    void onSetProgressBarVisibility(int visibility);
    void navigteToProductoFragment(Producto producto);
    void setProductosInfo(ArrayList<Producto> productos);
    void addListeners();
    void showError(String error);
    void clearSearchView();
}
