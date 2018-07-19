package com.nantia.repartonantia.cliente;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Envase;

import java.io.Serializable;

/**
 * Created by Emi on 3/6/2018.
 */

public class EnvaseEnPrestamo implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("envasetipos")
    private Envase envase;

    @SerializedName("cantidad")
    private int cantidad;

    public EnvaseEnPrestamo(long id, Envase envase, int cantidad) {
        this.id = id;
        this.envase = envase;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
