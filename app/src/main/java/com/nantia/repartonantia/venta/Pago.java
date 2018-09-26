package com.nantia.repartonantia.venta;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Pago implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "pago_pk")
  private transient long localPK;

  @ColumnInfo(name = "pago_id")
  @SerializedName("id")
  private long id;

  @ColumnInfo(name = "fecha_pago")
  @SerializedName("fechaPago")
  private long fechaPago;

  @ColumnInfo(name = "monto")
  @SerializedName("monto")
  private long monto;

  @ColumnInfo(name = "id_venta")
  @SerializedName("idVenta")
  private long idVenta;

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

  public long getFechaPago() {
    return fechaPago;
  }

  public void setFechaPago(long fechaPago) {
    this.fechaPago = fechaPago;
  }

  public long getMonto() {
    return monto;
  }

  public void setMonto(long monto) {
    this.monto = monto;
  }

  public long getIdVenta() {
    return idVenta;
  }

  public void setIdVenta(long idVenta) {
    this.idVenta = idVenta;
  }
}
