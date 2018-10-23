package com.nantia.repartonantia.venta;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Pago implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "pago_pk")
  private transient long localPK;

  @ColumnInfo(name = "pago_cliente_id")
  @SerializedName("clienteid")
  private long clienteId;

  @ColumnInfo(name = "fecha_pago")
  @SerializedName("fechapago")
  private String fechaPago;

  @ColumnInfo(name = "monto")
  @SerializedName("monto")
  private float monto;

  public long getLocalPK() {
    return localPK;
  }

  public void setLocalPK(long localPK) {
    this.localPK = localPK;
  }

  public String getFechaPago() {
    return fechaPago;
  }

  public void setFechaPago(String fechaPago) {
    this.fechaPago = fechaPago;
  }

  public float getMonto() {
    return monto;
  }

  public void setMonto(float monto) {
    this.monto = monto;
  }

  public long getClienteId() {
    return clienteId;
  }

  public void setClienteId(long clienteId) {
    this.clienteId = clienteId;
  }
}
