package com.nantia.repartonantia.listadeprecios;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Producto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Emi on 11/6/2018.
 */

public class ProductoLista implements Serializable {

    @SerializedName("producto")
    private Producto producto;

    @SerializedName("precio")
    private float precio;

    @SerializedName("actualizado")
    private Date actualizado;

    public ProductoLista(Producto producto, float precio, Date actualizado) {
        this.producto = producto;
        this.precio = precio;
        this.actualizado = actualizado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }
}
