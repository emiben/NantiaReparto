package com.nantia.repartonantia.reparto;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.stock.Stock;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by Emi on 28/7/2018.
 */

public class Vehiculo implements Serializable{

    @SerializedName("id")
    private long id;

    @SerializedName("matricula")
    private String matricula;

    @SerializedName("marca")
    private String marca;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("activo")
    private boolean activo;

    @SerializedName("stock")
    private Stock stock;

    public Vehiculo(long id, String matricula, String marca,
                    String modelo, String descripcion, boolean activo, Stock stock) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.activo = activo;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
