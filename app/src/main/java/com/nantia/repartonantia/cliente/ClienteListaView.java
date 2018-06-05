package com.nantia.repartonantia.cliente;

import com.nantia.repartonantia.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 28/5/2018.
 */

public interface ClienteListaView {
    void onSetProgressBarVisibility(int visibility);
    void setClienteInfo(ArrayList<Cliente> clientes);
    void clearSearchView();
    void addListeners();
    void showError(String error);
}
