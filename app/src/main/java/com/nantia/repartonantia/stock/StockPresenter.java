package com.nantia.repartonantia.stock;

import com.nantia.repartonantia.adapters.StockInfoPOJO;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;

import java.util.ArrayList;

/**
 * Created by Emi on 23/7/2018.
 */

public class StockPresenter {
    private final String TAG = StockPresenter.class.getName();
    private StockView view;

    public StockPresenter(StockView view) {
        this.view = view;
    }

    public void getStock(){
        Stock stock = null;
        if(DataHolder.getStock() != null){
            stock = DataHolder.getStock();
        }else{
            //TODO: Logica para traer el stock del server
        }

        if(stock != null){
            ArrayList<StockInfoPOJO> stockInfo=  new ArrayList<>();
            for(int i = 0; i < stock.getProductosStock().size(); i++){
                String primario = "";
                if (stock.getProductosStock().get(i).getProducto().getNombre() != null
                        && !stock.getProductosStock().get(i).getProducto().getNombre().isEmpty()){
                    primario = stock.getProductosStock().get(i).getProducto().getNombre();
                }
                if (stock.getProductosStock().get(i).getProducto().getPresentacion() != null
                        && !stock.getProductosStock().get(i).getProducto().getPresentacion().isEmpty()){
                    primario = primario + " " + stock.getProductosStock().get(i).getProducto().getPresentacion();
                }
                StockInfoPOJO stockInfoPOJO = new StockInfoPOJO(primario,
                        stock.getProductosStock().get(i).getCantidad(), "Producto");
                stockInfo.add(stockInfoPOJO);
            }
            for(int i = 0; i < stock.getEnvasesStock().size(); i++){
                String primario = "";
                if (stock.getEnvasesStock().get(i).getEnvase().getDescripcion() != null
                        && !stock.getEnvasesStock().get(i).getEnvase().getDescripcion().isEmpty()){
                    primario = stock.getEnvasesStock().get(i).getEnvase().getDescripcion();
                }
                StockInfoPOJO stockInfoPOJO = new StockInfoPOJO(primario,
                        stock.getEnvasesStock().get(i).getCantidad(), "Envase");
                stockInfo.add(stockInfoPOJO);
            }
            view.setStockInfo(stockInfo);
        }
    }

}
