package com.nantia.repartonantia.producto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 19/5/2018.
 */

@Entity
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "producto_pk")
    private transient long productoPK;

    @ColumnInfo(name = "producto_id")
    @SerializedName("productoId")
    private long id;

    @ColumnInfo(name = "nombre")
    @SerializedName("nombre")
    private String nombre;

    @ColumnInfo(name = "presentacion")
    @SerializedName("presentacion")
    private String presentacion;

    @ColumnInfo(name = "descripcion")
    @SerializedName("descripcion")
    private String descripcion;

    @ColumnInfo(name = "retornable")
    @SerializedName("retornable")
    private boolean retornable;

    @Embedded
    @SerializedName("envasesTipos")
    private Envase envase;


    public Producto() {
    }

    public Producto(long id, String nombre, String presentacion, String descripcion, boolean retornable) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.descripcion = descripcion;
        this.retornable = retornable;
    }


    public long getProductoPK() {
        return productoPK;
    }

    public void setProductoPK(long productoPK) {
        this.productoPK = productoPK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRetornable() {
        return retornable;
    }

    public void setRetornable(boolean retornable) {
        this.retornable = retornable;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }
}
