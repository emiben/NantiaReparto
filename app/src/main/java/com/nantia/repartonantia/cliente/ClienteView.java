package com.nantia.repartonantia.cliente;

import com.nantia.repartonantia.adapters.ClienteInfoPOJO;

import java.util.ArrayList;

/**
 * Created by Emi on 28/5/2018.
 */

public interface ClienteView {
    Cliente getCliente();
    void setClienteInfo(ArrayList<ClienteInfoPOJO> clientes, String nombre);
}
