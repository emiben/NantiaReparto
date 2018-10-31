package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 28/5/2018.
 */

public class Direccion implements Serializable {

    @ColumnInfo(name = "id_dir")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "direccion")
    @SerializedName("direccion")
    private String direccion;

    @ColumnInfo(name = "coord_lon")
    @SerializedName("coordLon")
    private String coordLon;

    @ColumnInfo(name = "coord_lat")
    @SerializedName("coordLat")
    private String coordLat;

    @ColumnInfo(name = "telefono")
    @SerializedName("telefono")
    private String telefono;

    @ColumnInfo(name = "ciudad")
    @SerializedName("ciudad")
    private String ciudad;

    @ColumnInfo(name = "departamento")
    @SerializedName("departamento")
    private String departamento;

    @ColumnInfo(name = "cod_postal")
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
