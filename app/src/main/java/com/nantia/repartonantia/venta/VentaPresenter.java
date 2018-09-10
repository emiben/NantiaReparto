package com.nantia.repartonantia.venta;

import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.IVA;

public class VentaPresenter {
    private String TAG = VentaPresenter.class.getName();
    private VentaView view;
    private Venta venta;

    public VentaPresenter(VentaView view, Venta venta) {
        this.view = view;
        this.venta = venta;
    }

    public void setData(String descuentoStr, String entregaStr){
        float totalVenta = getTotalVenta();
        float ivaTotal = (totalVenta * (IVA/100));
        float descuento = 0;
        float entrega = 0;
        float saldo = totalVenta;

        if(!descuentoStr.isEmpty() && Float.valueOf(descuentoStr) > 0){
            descuento = Float.valueOf(descuentoStr);
        }
        if(!entregaStr.isEmpty() && Float.valueOf(entregaStr) > 0){
            entrega = Float.valueOf(entregaStr);
        }

        venta.setIvaTotal(ivaTotal);
        venta.setTotalVenta(totalVenta);
        venta.setDescuento(descuento);
        venta.setPagoTotal(entrega);

        view.loadData(venta);
    }


    private float getTotalVenta(){
        float totalVenta = 0;
        List<ProductoVenta> prodsVenta = venta.getProductosVenta();
        for (ProductoVenta productoVenta : prodsVenta){
            totalVenta = totalVenta + productoVenta.getProductoTotal();
        }
        return totalVenta;
    }
}
