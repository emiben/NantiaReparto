package com.nantia.repartonantia.cliente;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 28/5/2018.
 */

public class Direccion implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("cordLon")
    private double cordLon;

    @SerializedName("cordLat")
    private double cordLat;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("ciudad")
    private String ciudad;

    @SerializedName("departamento")
    private String departamento;

    @SerializedName("codPostal")
    private String codPostal;

    public Direccion(int id, String direccion, double cordLon, double cordLat,
                     String telefono, String ciudad,
                     String departamento, String codPostal) {
        this.id = id;
        this.direccion = direccion;
        this.cordLon = cordLon;
        this.cordLat = cordLat;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codPostal = codPostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCordLon() {
        return cordLon;
    }

    public void setCordLon(float cordLon) {
        this.cordLon = cordLon;
    }

    public double getCordLat() {
        return cordLat;
    }

    public void setCordLat(float cordLat) {
        this.cordLat = cordLat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
}
