package com.nantia.repartonantia.producto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Emi on 3/6/2018.
 */

@Entity
public class Envase implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "envase_pk")
    private transient long envasePK;

    @ColumnInfo(name = "envase_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "envase_descripcion")
    @SerializedName("descripcion")
    private String descripcion;

    public Envase(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }


    public long getEnvasePK() {
        return envasePK;
    }

    public void setEnvasePK(long envasePK) {
        this.envasePK = envasePK;
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
}
