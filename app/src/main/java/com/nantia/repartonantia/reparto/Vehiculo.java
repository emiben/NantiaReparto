package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.stock.Stock;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by Emi on 28/7/2018.
 */

@Entity
public class Vehiculo implements Serializable{

    @PrimaryKey
    @ColumnInfo(name = "vehiculo_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "matricula")
    @SerializedName("matricula")
    private String matricula;

    @ColumnInfo(name = "marca")
    @SerializedName("marca")
    private String marca;

    @ColumnInfo(name = "modelo")
    @SerializedName("modelo")
    private String modelo;

    @ColumnInfo(name = "descripcion")
    @SerializedName("descripcion")
    private String descripcion;

    @ColumnInfo(name = "activo")
    @SerializedName("activo")
    private boolean activo;

    @Ignore
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
