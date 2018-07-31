package com.nantia.repartonantia.reparto;

import com.nantia.repartonantia.adapters.RepartoInfoPOJO;

import java.util.ArrayList;

/**
 * Created by Emi on 30/7/2018.
 */

public interface ListaRepartoView {
    void onSetProgressBarVisibility(int visibility);
    void setRepartosInfo(ArrayList<RepartoInfoPOJO> repartos);
    void addListeners();
    void showError(String error);
}
