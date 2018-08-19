package com.nantia.repartonantia.listadeprecios;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Emi on 11/6/2018.
 */

@Entity
public class ListaDePrecio implements Serializable {

    @ColumnInfo(name = "lista_precios_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "nombre_lista")
    @SerializedName("nombreLista")
    private String nombreLista;

    @ColumnInfo(name = "fecha_alta")
    @SerializedName("fechAlta")
    private String fechAlta;

    @SerializedName("setProductoLista")
    @TypeConverters(ProductoListaTypeConverter.class)
    private ArrayList<ProductoLista> productosLista;

    public ListaDePrecio(long id, String nombreLista, String fechAlta, ArrayList<ProductoLista> productosLista) {
        this.id = id;
        this.nombreLista = nombreLista;
        this.fechAlta = fechAlta;
        this.productosLista = productosLista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getFechAlta() {
        return fechAlta;
    }

    public void setFechAlta(String fechAlta) {
        this.fechAlta = fechAlta;
    }

    public ArrayList<ProductoLista> getProductosLista() {
        return productosLista;
    }

    public void setProductosLista(ArrayList<ProductoLista> productosLista) {
        this.productosLista = productosLista;
    }
}
