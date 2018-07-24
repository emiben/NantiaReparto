package com.nantia.repartonantia.stock;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Emi on 19/7/2018.
 */

public class Stock implements Serializable{

    @SerializedName("id")
    private long id;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("setEnvaseStock")
    private ArrayList<EnvaseStock> envasesStock;

    @SerializedName("setProductoStock")
    private ArrayList<ProductoStock> productosStock;

    public Stock(long id, String fecha, ArrayList<EnvaseStock> envasesStock, ArrayList<ProductoStock> productosStock) {
        this.id = id;
        this.fecha = fecha;
        this.envasesStock = envasesStock;
        this.productosStock = productosStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<EnvaseStock> getEnvasesStock() {
        return envasesStock;
    }

    public void setEnvasesStock(ArrayList<EnvaseStock> envasesStock) {
        this.envasesStock = envasesStock;
    }

    public ArrayList<ProductoStock> getProductosStock() {
        return productosStock;
    }

    public void setProductosStock(ArrayList<ProductoStock> productosStock) {
        this.productosStock = productosStock;
    }
}
