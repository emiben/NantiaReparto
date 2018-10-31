package com.nantia.repartonantia.adapters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 29/7/2018.
 */

public class RepartoInfoPOJO implements Serializable{

    @SerializedName("idReparto")
    private long id;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("descripcionVehiculo")
    private String vehiculo;

    @SerializedName("dias")
    private String dia;

    public RepartoInfoPOJO(long id, String descripcion, String vehiculo, String dia) {
        this.id = id;
        this.descripcion = descripcion;
        this.vehiculo = vehiculo;
        this.dia = dia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
