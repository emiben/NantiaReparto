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
    private float cordLon;

    @SerializedName("cordLat")
    private float cordLat;

    @SerializedName("telefono")
    private int telefono;

    @SerializedName("tel")
    private String esquina1;

    @SerializedName("ciudad")
    private String ciudad;

    @SerializedName("departamento")
    private String departamento;

    @SerializedName("codPostal")
    private String codPostal;

    public Direccion(int id, String direccion, float cordLon, float cordLat,
                     int telefono, String esquina1, String ciudad,
                     String departamento, String codPostal) {
        this.id = id;
        this.direccion = direccion;
        this.cordLon = cordLon;
        this.cordLat = cordLat;
        this.telefono = telefono;
        this.esquina1 = esquina1;
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

    public float getCordLon() {
        return cordLon;
    }

    public void setCordLon(float cordLon) {
        this.cordLon = cordLon;
    }

    public float getCordLat() {
        return cordLat;
    }

    public void setCordLat(float cordLat) {
        this.cordLat = cordLat;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEsquina1() {
        return esquina1;
    }

    public void setEsquina1(String esquina1) {
        this.esquina1 = esquina1;
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
