package com.nantia.repartonantia.venta;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Producto;

import java.io.Serializable;

public class ProductoVenta implements Serializable{

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    @Embedded
    @SerializedName("producto")
    private Producto producto;


    @ColumnInfo(name = "cantidad")
    @SerializedName("cantidad")
    private int cantidad;

    @ColumnInfo(name = "precio_unitario")
    @SerializedName("precioUnitario")
    private float precioUnitario;

    @ColumnInfo(name = "producto_total")
    @SerializedName("total")
    private float productoTotal;


    public ProductoVenta(long id, Producto producto, int cantidad, float precioUnitario, float productoTotal) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.productoTotal = productoTotal;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getProductoTotal() {
        return productoTotal;
    }

    public void setProductoTotal(float productoTotal) {
        this.productoTotal = productoTotal;
    }
}
