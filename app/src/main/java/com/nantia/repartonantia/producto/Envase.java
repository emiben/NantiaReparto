package com.nantia.repartonantia.producto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 3/6/2018.
 */

public class Envase implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("descripcion")
    private String descripcion;

    public Envase(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
