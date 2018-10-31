package com.nantia.repartonantia.stock;

import com.nantia.repartonantia.adapters.StockInfoPOJO;

import java.util.ArrayList;

/**
 * Created by Emi on 23/7/2018.
 */

public interface StockView {
    void setStockInfo(ArrayList<StockInfoPOJO> stock);
    void addListeners();
    void onSetProgressBarVisibility(int visibility);
    void showError(String error);
    void finishActivity();
}
