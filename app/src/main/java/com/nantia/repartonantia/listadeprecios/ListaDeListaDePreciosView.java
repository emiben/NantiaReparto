package com.nantia.repartonantia.listadeprecios;

import com.nantia.repartonantia.cliente.Cliente;

import java.util.ArrayList;

/**
 * Created by Emi on 6/7/2018.
 */

public interface ListaDeListaDePreciosView {
    void onSetProgressBarVisibility(int visibility);
    void setListasDePrecioInfo(ArrayList<ListaDePrecio> listasDePrecios);
    void addListeners();
    void showError(String error);
}
