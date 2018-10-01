package com.nantia.repartonantia.venta;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.AppDatabase;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.stock.EnvaseStock;
import com.nantia.repartonantia.stock.ProductoStock;
import com.nantia.repartonantia.stock.Stock;
import com.nantia.repartonantia.stock.StockService;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.utils.FechaHelper;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nantia.repartonantia.utils.Constantes.HTTP_CREATED;
import static com.nantia.repartonantia.utils.Constantes.HTTP_OK;
import static com.nantia.repartonantia.utils.Constantes.IVA;

public class VentaPresenter {
    private String TAG = VentaPresenter.class.getName();
    private AppDatabase db;
    private VentaView view;
    private Venta venta;

    public VentaPresenter(VentaView view, Venta venta, AppDatabase db) {
        this.view = view;
        this.venta = venta;
        this.db = db;
    }

    public void setData(String descuentoStr, String entregaStr){
        float totalVenta = getTotalVenta();
        float ivaTotal = (totalVenta * ((float)IVA/100));
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

        view.updateData(venta);
    }

    public void finalizarVenta(){
        Usuario vendedor;
        Pago pago = new Pago();
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
        cliente.setDifSaldo(venta.calcularSaldo());
        cliente.setActualizado(false);
        DataHolder.getClientes().remove(clienteOrig);
        DataHolder.getClientes().add(cliente);

        venta.setFecha(FechaHelper.getStringDate());
        venta.setReaprtoID(DataHolder.getReparto().getId());
        venta.setActualizado(false);

        pago.setFechaPago(FechaHelper.getStringDate());
        pago.setMonto(venta.getPagoTotal());

        venta.setPago(pago);

        updateStock(venta.getProductosVenta());
        sendVenta(venta);
    }


    private void updateStock(List<ProductoVenta> prodsVenta){
        Stock stock = DataHolder.getReparto().getVehiculo().getStock();
        for(ProductoVenta productoVenta : prodsVenta){
            boolean encontreProd = false;
            Iterator<ProductoStock> prodIterator = stock.getProductosStock().iterator();
            while (prodIterator.hasNext() && !encontreProd){
                ProductoStock productoStock = prodIterator.next();
                if(productoVenta.getProducto().getId() == productoStock.getProducto().getId()){
                    stock.getProductosStock().remove(productoStock);
                    productoStock.setCantidad(productoStock.getCantidad() - productoVenta.getCantidad());
                    stock.getProductosStock().add(productoStock);
                    encontreProd = true;
                    if(productoVenta.getProducto().isRetornable()){
                        boolean encontreEnv = false;
                        Iterator<EnvaseStock> envaseIterator = stock.getEnvasesStock().iterator();
                        while (envaseIterator.hasNext() && !encontreEnv){
                            EnvaseStock envaseStock = envaseIterator.next();
                            if(productoVenta.getProducto().getEnvase().getId() == envaseStock.getEnvase().getId()){
                                stock.getEnvasesStock().remove(envaseStock);
                                envaseStock.setCantidad(envaseStock.getCantidad() + productoVenta.getCantidad());
                                stock.getEnvasesStock().add(envaseStock);
                                encontreEnv = true;
                            }
                        }
                    }
                }
            }
        }
        stock.setActualizado(false);
        DataHolder.getReparto().getVehiculo().setStock(stock);
        sendStock(stock);
    }


    private void sendVenta(final Venta venta) {
        view.onSetProgressBarVisibility(View.VISIBLE);
        VentaService ventaService = RetrofitClientInstance.getRetrofitInstance().create(VentaService.class);
        Call<Venta> call = ventaService.saveVenta(venta);
        call.enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {
                if(response.code() == HTTP_CREATED){
                    response.body().setActualizado(true);
                    saveVenta(response.body());
                    view.onSetProgressBarVisibility(View.GONE);
                    Log.i(TAG, "Venta Creada OK");
                }else {
                    saveVenta(venta);
                    Log.e(TAG, "Venta Creada ERROR: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {
                saveVenta(venta);
                Log.e(TAG, "Venta Creada ERROR: " + t.getMessage());
            }
        });
    }

    private void saveVenta(final Venta venta){
        if(DataHolder.getVentas() == null){
            DataHolder.setVentas(new ArrayList<Venta>());
        }
        DataHolder.getVentas().remove(venta);
        DataHolder.getVentas().add(venta);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.ventaDao().insertAll(venta);
                Log.i(TAG, "Venta guardodo en base");
            }
        });
    }

    private void sendStock(final Stock stock) {
        view.onSetProgressBarVisibility(View.VISIBLE);
        StockService stockService = RetrofitClientInstance.getRetrofitInstance().create(StockService.class);
        Call<Stock> call = stockService.updateStock(stock.getId(), stock);
        call.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, final Response<Stock> response) {
                if(response.code() == HTTP_OK){
                    response.body().setActualizado(true);
                    saveStock(response.body());
                    Log.i(TAG, "Stock update OK");
                }else {
                    stock.setActualizado(false);
                    saveStock(stock);
                    Log.e(TAG, "Stock update fallo:" + response.message());
                }
                view.onSetProgressBarVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {
                stock.setActualizado(false);
                saveStock(stock);
                view.onSetProgressBarVisibility(View.GONE);
                Log.e(TAG, "Stock update fallo:" + t.getMessage());
            }
        });
    }

    private void saveStock(final Stock stock){
        DataHolder.getReparto().getVehiculo().setStock(stock);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.stockDao().updateStock(stock);
                Log.i(TAG, "Stock guardado en base");
            }
        });
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
