package com.nantia.repartonantia.venta;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.usuario.Usuario;

import java.io.Serializable;
import java.util.List;

@Entity
public class Venta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "venta_pk")
    private transient long localPK;

    @ColumnInfo(name = "venta_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "fecha")
    @SerializedName("fecha")
    private String fecha;

    @Embedded
    @SerializedName("usuario")
    private Usuario usuario;

    @Embedded
    @SerializedName("cliente")
    private Cliente cliente;

    @SerializedName("productosVenta")
    @TypeConverters(ProductoVentaTypeConverter.class)
    private List<ProductoVenta> productosVenta;

    @ColumnInfo(name = "descuento")
    @SerializedName("descuento")
    private float descuento;

    @ColumnInfo(name = "total_venta")
    @SerializedName("totalVenta")
    private float totalVenta;

    @ColumnInfo(name = "iva_total")
    @SerializedName("ivaTotal")
    private float ivaTotal;

    @ColumnInfo(name = "pago_total")
    @SerializedName("pagoTotal")
    private float pagoTotal;

    @ColumnInfo(name = "reparto_id")
    @SerializedName("reparto")
    private long reaprtoID;

    @ColumnInfo(name = "actualizado_venta")
    private transient boolean actualizado;

    @Embedded
    @SerializedName("pago")
    private Pago pago;

    public Venta(){}

    public Venta(long localPK, long id, String fecha, Usuario usuario, Cliente cliente,
                 List<ProductoVenta> productosVenta, float descuento, float totalVenta,
                 float ivaTotal, float pagoTotal, long reaprtoID, boolean actualizado, Pago pago) {
        this.localPK = localPK;
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.cliente = cliente;
        this.productosVenta = productosVenta;
        this.descuento = descuento;
        this.totalVenta = totalVenta;
        this.ivaTotal = ivaTotal;
        this.pagoTotal = pagoTotal;
        this.reaprtoID = reaprtoID;
        this.actualizado = actualizado;
        this.pago = pago;
    }

    public long getLocalPK() {
        return localPK;
    }

    public void setLocalPK(long localPK) {
        this.localPK = localPK;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoVenta> getProductosVenta() {
        return productosVenta;
    }

    public void setProductosVenta(List<ProductoVenta> productosVenta) {
        this.productosVenta = productosVenta;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

    public float getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(float ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public float getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(float pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public long getReaprtoID() {
        return reaprtoID;
    }

    public void setReaprtoID(long reaprtoID) {
        this.reaprtoID = reaprtoID;
    }

    public boolean isActualizado() {
        return actualizado;
    }

    public void setActualizado(boolean actualizado) {
        this.actualizado = actualizado;
    }

    public float calcularSaldo(){
        return (totalVenta - descuento - pagoTotal);
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
