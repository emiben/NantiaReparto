package com.nantia.repartonantia.reparto;

import android.view.View;

import com.nantia.repartonantia.adapters.RepartoInfoPOJO;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 30/7/2018.
 */


public class ListaRepartoPresenter {
    private final String TAG = ListaRepartoPresenter.class.getName();
    private ListaRepartoView view;

    ListaRepartoPresenter(ListaRepartoView view) {
        this.view = view;
    }

    void getRepartosInfo(){
        view.onSetProgressBarVisibility(View.VISIBLE);
        RepartoService service = RetrofitClientInstance.getRetrofitInstance().create(RepartoService.class);
        Call<ArrayList<RepartoInfoPOJO>> call = service.getRepartosInfo();
        call.enqueue(new Callback<ArrayList<RepartoInfoPOJO>>() {
            @Override
            public void onResponse(Call<ArrayList<RepartoInfoPOJO>> call, Response<ArrayList<RepartoInfoPOJO>> response) {
                if(response.body() != null){
                    view.setRepartosInfo(response.body());
                    view.addListeners();
                    view.onSetProgressBarVisibility(View.GONE);
                }else {
                    view.onSetProgressBarVisibility(View.GONE);
                    //TODO: implementar el manejo de errores
                    view.showError("implementar el manejo de errores getRepartosInfo");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RepartoInfoPOJO>> call, Throwable t) {
                view.onSetProgressBarVisibility(View.GONE);
                view.showError(t.getMessage());
            }
        });
    }

    void getReparto(final long repartoId){
        view.onSetProgressBarVisibility(View.VISIBLE);
        RepartoService service = RetrofitClientInstance.getRetrofitInstance().create(RepartoService.class);
        Call<Reparto> call = service.getReparto(repartoId);
        call.enqueue(new Callback<Reparto>() {
            @Override
            public void onResponse(Call<Reparto> call, Response<Reparto> response) {
                if(response.body() != null){
                    view.onSetProgressBarVisibility(View.GONE);
                    DataHolder.setReparto(response.body());
                    view.navigateToReparto(response.body());
                }
                else {
                    view.onSetProgressBarVisibility(View.GONE);
                    //TODO: implementar el manejo de errores
                    view.showError("implementar el manejo de errores getReparto");
                }
            }

            @Override
            public void onFailure(Call<Reparto> call, Throwable t) {
                view.onSetProgressBarVisibility(View.GONE);
                view.showError(t.getMessage());
            }
        });
    }

}
