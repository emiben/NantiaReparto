package com.nantia.repartonantia.listadeprecios;

import com.nantia.repartonantia.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 6/7/2018.
 */

public interface ListaDeListaDePreciosView {
    void onSetProgressBarVisibility(int visibility);
    void setListasDePrecioInfo(List<ListaDePrecio> listasDePrecios);
    void addListeners();
    void showError(String error);
    void showError(int resourceId);
}
