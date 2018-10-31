package com.nantia.repartonantia.listadeprecios;

import android.arch.persistence.room.ColumnInfo;
import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Producto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Emi on 11/6/2018.
 */

public class ProductoLista implements Serializable {

    @ColumnInfo(name = "prod_lista_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "producto")
    @SerializedName("productos")
    private Producto producto;

    @ColumnInfo(name = "precio")
    @SerializedName("precio")
    private float precio;

    @ColumnInfo(name = "actualizado")
    @SerializedName("actualizado")
    private String actualizado;


    public ProductoLista(long id, Producto producto, float precio, String actualizado) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.actualizado = actualizado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getActualizado() {
        return actualizado;
    }

    public void setActualizado(String actualizado) {
        this.actualizado = actualizado;
    }
}
