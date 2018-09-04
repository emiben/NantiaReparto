package com.nantia.repartonantia.stock;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 19/7/2018.
 */

@Entity
public class Stock implements Serializable{

    @PrimaryKey
    @ColumnInfo(name = "stock_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "fecha")
    @SerializedName("fecha")
    private String fecha;

    @SerializedName("setEnvaseStock")
    @TypeConverters(EnvaseStockTypeConverter.class)
    private List<EnvaseStock> envasesStock;

    @SerializedName("setProductoStock")
    @TypeConverters(ProductoStockTypeConverter.class)
    private List<ProductoStock> productosStock;

    @ColumnInfo(name = "actualizado")
    private transient boolean actualizado;

    public Stock(long id, String fecha, List<EnvaseStock> envasesStock, List<ProductoStock> productosStock) {
        this.id = id;
        this.fecha = fecha;
        this.envasesStock = envasesStock;
        this.productosStock = productosStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<EnvaseStock> getEnvasesStock() {
        return envasesStock;
    }

    public void setEnvasesStock(ArrayList<EnvaseStock> envasesStock) {
        this.envasesStock = envasesStock;
    }

    public List<ProductoStock> getProductosStock() {
        return productosStock;
    }

    public void setProductosStock(ArrayList<ProductoStock> productosStock) {
        this.productosStock = productosStock;
    }

    public boolean isActualizado() {
        return actualizado;
    }

    public void setActualizado(boolean actualizado) {
        this.actualizado = actualizado;
    }
}
