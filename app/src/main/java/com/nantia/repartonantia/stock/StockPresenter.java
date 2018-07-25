package com.nantia.repartonantia.stock;

import android.util.Log;
import android.view.View;
import com.nantia.repartonantia.adapters.StockInfoPOJO;
import com.nantia.repartonantia.data.DataHolder;

import com.nantia.repartonantia.utils.RetrofitClientInstance;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        view.onSetProgressBarVisibility(View.VISIBLE);
        if(DataHolder.getStock() != null){
            Stock stock = DataHolder.getStock();
            view.setStockInfo(prepareStockInfoPOJO(stock));
            view.addListeners();
            view.onSetProgressBarVisibility(View.GONE);
        }else{
            StockService stockService = RetrofitClientInstance.getRetrofitInstance().create(StockService.class);
            //TODO: Traer el id del stock del reparto
            Call<Stock> call = stockService.getStock(1);
            call.enqueue(new Callback<Stock>() {
                @Override public void onResponse(Call<Stock> call, Response<Stock> response) {
                    DataHolder.setStock(response.body());
                    view.setStockInfo(prepareStockInfoPOJO(response.body()));
                    view.addListeners();
                    view.onSetProgressBarVisibility(View.GONE);
                }

                @Override public void onFailure(Call<Stock> call, Throwable t) {
                    view.onSetProgressBarVisibility(View.GONE);
                    Log.e(TAG, t.getMessage());
                    view.showError(t.getMessage());
                }
            });

        }
    }

    private ArrayList<StockInfoPOJO> prepareStockInfoPOJO(Stock stock){
        ArrayList<StockInfoPOJO> stockInfo=  new ArrayList<>();
        if(stock != null){
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
        }
        return stockInfo;
    }

}
