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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vehiculo_pk")
    private transient long vehiculoPK;

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

    @ColumnInfo(name = "coord_lon")
    @SerializedName("coordLon")
    private String coordLon;

    @ColumnInfo(name = "coord_lat")
    @SerializedName("coordLat")
    private String coordLat;

    @ColumnInfo(name = "activo")
    @SerializedName("activo")
    private boolean activo;


    public Vehiculo(){}

    public Vehiculo(long id, String matricula, String marca,
                    String modelo, String descripcion, boolean activo) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public long getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(long vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
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

    public String getCoordLon() {
        return coordLon;
    }

    public void setCoordLon(String coordLon) {
        this.coordLon = coordLon;
    }

    public String getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(String coordLat) {
        this.coordLat = coordLat;
    }
}
