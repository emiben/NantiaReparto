package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.usuario.Usuario;

import java.io.Serializable;

/**
 * Created by Emi on 28/7/2018.
 */

@Entity
public class Reparto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reparto_pk")
    private transient long repartoPK;

    @ColumnInfo(name = "reparto_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "descripcion")
    @SerializedName("descripcion")
    private String descripcion;

    @Ignore
    @SerializedName("vendedor1")
    private Usuario vendedor1;

    @Ignore
    @SerializedName("vendedor2")
    private Usuario vendedor2;

    @Ignore
    @SerializedName("vehiculo")
    private Vehiculo vehiculo;

    @ColumnInfo(name = "fecha_reparto")
    @SerializedName("fecha")
    private String fecha;

    @Ignore
    @SerializedName("ruta")
    private Ruta ruta;

    @ColumnInfo(name = "estado")
    @SerializedName("estado")
    private String estado;

    public Reparto(){};

    public Reparto(long id, String descripcion, Usuario vendedor1,
                   Usuario vendedor2, Vehiculo vehiculo, String fecha, Ruta ruta, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.vendedor1 = vendedor1;
        this.vendedor2 = vendedor2;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.ruta = ruta;
        this.estado = estado;
    }

    public long getRepartoPK() {
        return repartoPK;
    }

    public void setRepartoPK(long repartoPK) {
        this.repartoPK = repartoPK;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
