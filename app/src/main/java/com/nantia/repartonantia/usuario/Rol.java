package com.nantia.repartonantia.usuario;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 6/7/2018.
 */

public class Rol implements Serializable {

    @ColumnInfo(name = "rol_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "nombre_rol")
    @SerializedName("nombreRol")
    private String nombreRol;

    public Rol(long id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
