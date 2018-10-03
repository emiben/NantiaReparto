package com.nantia.repartonantia.venta;

import android.view.View;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ProductoLista;
import com.nantia.repartonantia.stock.ProductoStock;
import com.nantia.repartonantia.stock.Stock;

import java.util.ArrayList;
import java.util.List;

public class ListaProductoVentaPresenter {
    private final String TAG = ListaProductoVentaPresenter.class.getName();
    private ListaProductoVentaView view;

    private Venta venta;
    private List<ProductoVenta> prodsVenta;
    private ListaDePrecio listaDePrecio;
    private Stock stock;
    private Cliente cliente;

    public ListaProductoVentaPresenter(ListaProductoVentaView view, Cliente cliente) {
        this.view = view;
        this.cliente = cliente;
        venta = new Venta();
        prodsVenta = new ArrayList<>();
    }

    public void getData(){
        view.onSetProgressBarVisibility(View.VISIBLE);
        if(DataHolder.getReparto() != null && DataHolder.getReparto().getVehiculo() != null){
            stock = DataHolder.getReparto().getVehiculo().getStock();
        }
        listaDePrecio = DataHolder.getListaDePrecioById(cliente.getIdLista());
        if(stock != null && listaDePrecio.getProductosLista() != null){
            view.loadData(listaDePrecio.getProductosLista());
            view.setListeners();
        }else {
            view.showStockNoAsociadoError();
            view.finishActivity();
        }
        view.onSetProgressBarVisibility(View.GONE);
    }

    public List<ProductoLista> getProdsLista(){
        return listaDePrecio.getProductosLista();
    }

    public Venta getVenta() {
        venta.setProductosVenta(prodsVenta);
        venta.setCliente(cliente);
        return venta;
    }

    public void addOrUpdateArticulo(ProductoLista productoLista, String cantidad){
        float cantStock = getCantidadEnStock(productoLista.getProducto().getId());
        int cant = 0;
        if(!cantidad.isEmpty()) cant = Integer.valueOf(cantidad);
        ProductoVenta productoVenta = getProdEnLista(productoLista.getProducto().getId());
        if(cant == 0){
            if(productoVenta != null) prodsVenta.remove(productoVenta);
        }else {
            if(cant > cantStock){
                view.showStockError(cantStock);
            }else {
                if(productoVenta == null){
                    productoVenta = new ProductoVenta(0, productoLista.getProducto(), cant,
                            productoLista.getPrecio(), (cant * productoLista.getPrecio()));
                }else {
                    prodsVenta.remove(productoVenta);
                    productoVenta.setCantidad(cant);
                    productoVenta.setProductoTotal((cant * productoLista.getPrecio()));
                }
                prodsVenta.add(productoVenta);
            }
        }
        view.updateCantidadCarro(prodsVenta.size());
    }

    public int getCantArticulos(){
        if(prodsVenta != null){
            return prodsVenta.size();
        }else{
            return 0;
        }
    }

    private ProductoVenta getProdEnLista(long prodId){
        for (ProductoVenta productoVenta : prodsVenta){
            if(productoVenta.getProducto().getId() == prodId){
                return productoVenta;
            }
        }
        return null;
    }

    private float getCantidadEnStock(long prodId){
        List<ProductoStock> productosStock = stock.getProductosStock();
        for (ProductoStock productoStock : productosStock){
            if(productoStock.getProducto().getId() == prodId){
                return productoStock.getCantidad();
            }
        }
        return 0;
    }

}
