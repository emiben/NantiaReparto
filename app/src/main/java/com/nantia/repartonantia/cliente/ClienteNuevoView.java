package com.nantia.repartonantia.cliente;

import com.nantia.repartonantia.producto.Envase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 29/5/2018.
 */

public interface ClienteNuevoView {
    void saveCliente();
    void setEnvases(List<Envase> envases);
    void onSetProgressBarVisibility(int visibility);
    void showError(String error);
    void navigateToClienteFragment(Cliente cliente);
}
