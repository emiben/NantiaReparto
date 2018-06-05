package com.nantia.repartonantia.cliente;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Envase;

import java.io.Serializable;

/**
 * Created by Emi on 3/6/2018.
 */

public class EnvaseEnPrestamo implements Serializable {

    @SerializedName("envase")
    private Envase envase;

    @SerializedName("cantidad")
    private int cantidad;

    public EnvaseEnPrestamo(Envase envase, int cantidad) {
        this.envase = envase;
        this.cantidad = cantidad;
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
