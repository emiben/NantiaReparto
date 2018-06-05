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

    @SerializedName("esquina1")
    private String esquina1;

    @SerializedName("esquina2")
    private String esquina2;

    @SerializedName("observaciones")
    private String observaciones;

    @SerializedName("principal")
    private boolean principal;

    @SerializedName("dirCobro")
    private boolean dirCobro;


    public Direccion(int id, String direccion, float cordLon, float cordLat, int telefono, String esquina1,
                     String esquina2, String observaciones, boolean principal, boolean dirCobro) {
        this.id = id;
        this.direccion = direccion;
        this.cordLon = cordLon;
        this.cordLat = cordLat;
        this.telefono = telefono;
        this.esquina1 = esquina1;
        this.esquina2 = esquina2;
        this.observaciones = observaciones;
        this.principal = principal;
        this.dirCobro = dirCobro;
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

    public String getEsquina2() {
        return esquina2;
    }

    public void setEsquina2(String esquina2) {
        this.esquina2 = esquina2;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isDirCobro() {
        return dirCobro;
    }

    public void setDirCobro(boolean dirCobro) {
        this.dirCobro = dirCobro;
    }
}
