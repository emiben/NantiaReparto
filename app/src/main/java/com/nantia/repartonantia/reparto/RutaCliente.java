package com.nantia.repartonantia.reparto;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.cliente.Cliente;

import java.io.Serializable;

/**
 * Created by Emi on 28/7/2018.
 */

public class RutaCliente implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("cliente")
    private Cliente cliente;

    @SerializedName("ordenVisita")
    private int ordenVisita;

    public RutaCliente(long id, Cliente cliente, int ordenVisita) {
        this.id = id;
        this.cliente = cliente;
        this.ordenVisita = ordenVisita;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getOrdenVisita() {
        return ordenVisita;
    }

    public void setOrdenVisita(int ordenVisita) {
        this.ordenVisita = ordenVisita;
    }
}
