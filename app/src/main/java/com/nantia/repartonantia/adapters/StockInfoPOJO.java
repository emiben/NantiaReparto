package com.nantia.repartonantia.adapters;

/**
 * Created by Emi on 23/7/2018.
 */

public class StockInfoPOJO {
    private String primario;
    private float cantidad;
    private String secudario;

    public StockInfoPOJO(String primario, float cantidad, String secudario) {
        this.primario = primario;
        this.cantidad = cantidad;
        this.secudario = secudario;
    }

    public String getPrimario() {
        return primario;
    }

    public void setPrimario(String primario) {
        this.primario = primario;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getSecudario() {
        return secudario;
    }

    public void setSecudario(String secudario) {
        this.secudario = secudario;
    }
}
