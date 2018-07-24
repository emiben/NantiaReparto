package com.nantia.repartonantia.stock;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Producto;

import java.io.Serializable;

/**
 * Created by Emi on 19/7/2018.
 */

public class ProductoStock implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("cantidad")
    private float cantidad;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("producto")
    private Producto producto;

    public ProductoStock(long id, float cantidad, String fecha, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.producto = producto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
