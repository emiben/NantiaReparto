package com.nantia.repartonantia.producto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 19/5/2018.
 */

public class Producto implements Serializable {

    @SerializedName("productoId")
    private long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("presentacion")
    private String presentacion;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("retornable")
    private boolean retornable;


    public Producto() {
    }

    public Producto(long id, String nombre, String presentacion, String descripcion, boolean retornable) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.descripcion = descripcion;
        this.retornable = retornable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRetornable() {
        return retornable;
    }

    public void setRetornable(boolean retornable) {
        this.retornable = retornable;
    }
}
