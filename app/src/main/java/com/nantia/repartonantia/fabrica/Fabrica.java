package com.nantia.repartonantia.fabrica;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.stock.Stock;

import java.io.Serializable;

/**
 * Created by Emi on 28/7/2018.
 */

public class Fabrica implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("stock")
    private Stock stock;

    public Fabrica(long id, String nombre, Stock stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
