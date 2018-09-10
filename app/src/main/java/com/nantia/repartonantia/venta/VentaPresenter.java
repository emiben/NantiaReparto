package com.nantia.repartonantia.venta;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.stock.EnvaseStock;
import com.nantia.repartonantia.stock.ProductoStock;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.utils.FechaHelper;
import java.util.Iterator;
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

    public void finalizarVenta(){
        //TODO: generar pago
        Usuario vendedor;
        Cliente clienteOrig = venta.getCliente();
        Cliente cliente = venta.getCliente();
        if(view.isVendedor1Checked()){
            vendedor = DataHolder.getReparto().getVendedor1();
            vendedor.setSaldoCaja(vendedor.getSaldoCaja() + venta.getPagoTotal());
            vendedor.setActualizado(false);
            DataHolder.getReparto().setVendedor1(vendedor);
            venta.setUsuario(vendedor);
        }else {
            vendedor = DataHolder.getReparto().getVendedor2();
            vendedor.setSaldoCaja(vendedor.getSaldoCaja() + venta.getPagoTotal());
            vendedor.setActualizado(false);
            DataHolder.getReparto().setVendedor2(vendedor);
            venta.setUsuario(DataHolder.getReparto().getVendedor2());
        }

        cliente.setSaldo(cliente.getSaldo() + venta.calcularSaldo());
        cliente.setActualizado(false);
        DataHolder.getClientes().remove(clienteOrig);
        DataHolder.getClientes().add(cliente);

        venta.setFecha(FechaHelper.getStringDate());
        venta.setReaprtoID(DataHolder.getReparto().getId());
        venta.setActualizado(false);

        updateStock(venta.getProductosVenta());
        saveAndSendVenta(venta);
    }



    private void updateStock(List<ProductoVenta> prodsVenta){
        for(ProductoVenta productoVenta : prodsVenta){
            boolean encontreProd = false;
            Iterator<ProductoStock> prodIterator = DataHolder.getStock().getProductosStock().iterator();
            while (prodIterator.hasNext() && !encontreProd){
                if(productoVenta.getProducto().getId() == ((ProductoStock)prodIterator).getProducto().getId()){
                    ProductoStock productoStock = (ProductoStock)prodIterator;
                    DataHolder.getStock().getProductosStock().remove(productoStock);
                    productoStock.setCantidad(productoStock.getCantidad() - productoVenta.getCantidad());
                    DataHolder.getStock().getProductosStock().add(productoStock);
                    encontreProd = true;
                    if(productoVenta.getProducto().isRetornable()){
                        boolean encontreEnv = false;
                        Iterator<EnvaseStock> envaseIterator = DataHolder.getStock().getEnvasesStock().iterator();
                        while (envaseIterator.hasNext() && !encontreEnv){
                            if(productoVenta.getProducto().getEnvase().getId() == ((EnvaseStock)envaseIterator).getEnvase().getId()){
                                EnvaseStock envaseStock = (EnvaseStock)envaseIterator;
                                DataHolder.getStock().getEnvasesStock().remove(envaseStock);
                                envaseStock.setCantidad(envaseStock.getCantidad() + productoVenta.getCantidad());
                                DataHolder.getStock().getEnvasesStock().add(envaseStock);
                                encontreEnv = true;
                            }
                        }
                    }
                }
            }
        }
        //TODO: guardar Stock en base de datos
    }


    private void saveAndSendVenta(Venta venta){

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
