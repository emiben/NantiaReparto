package com.nantia.repartonantia.stock;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.producto.Envase;

import java.io.Serializable;

/**
 * Created by Emi on 22/7/2018.
 */

public class EnvaseStock implements Serializable {

    @ColumnInfo(name = "envase_stock_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "cantidad_env_stk")
    @SerializedName("cantidad")
    private float cantidad;

    @ColumnInfo(name = "fecha_env_stk")
    @SerializedName("fecha")
    private String fecha;

    @ColumnInfo(name = "envase")
    @SerializedName("envasesTipos")
    private Envase envase;

    public EnvaseStock(long id, float cantidad, String fecha, Envase envase) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.envase = envase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }
}
