package com.nantia.repartonantia.reparto;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.usuario.Usuario;

import java.io.Serializable;

/**
 * Created by Emi on 28/7/2018.
 */

public class Reparto implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("vendedor1")
    private Usuario vendedor1;

    @SerializedName("vendedor2")
    private Usuario vendedor2;

    @SerializedName("vehiculo")
    private Vehiculo vehiculo;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("ruta")
    private Ruta ruta;

    public Reparto(long id, String descripcion, Usuario vendedor1,
                   Usuario vendedor2, Vehiculo vehiculo, String fecha, Ruta ruta) {
        this.id = id;
        this.descripcion = descripcion;
        this.vendedor1 = vendedor1;
        this.vendedor2 = vendedor2;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.ruta = ruta;
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

    public Usuario getVendedor1() {
        return vendedor1;
    }

    public void setVendedor1(Usuario vendedor1) {
        this.vendedor1 = vendedor1;
    }

    public Usuario getVendedor2() {
        return vendedor2;
    }

    public void setVendedor2(Usuario vendedor2) {
        this.vendedor2 = vendedor2;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
