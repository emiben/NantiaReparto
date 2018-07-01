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

    @SerializedName("coordLon")
    private String coordLon;

    @SerializedName("coordLat")
    private String coordLat;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("ciudad")
    private String ciudad;

    @SerializedName("departamento")
    private String departamento;

    @SerializedName("codPostal")
    private String codPostal;

    public Direccion(int id, String direccion, String coordLon, String coordLat,
                     String telefono, String ciudad,
                     String departamento, String codPostal) {
        this.id = id;
        this.direccion = direccion;
        this.coordLon = coordLon;
        this.coordLat = coordLat;
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
