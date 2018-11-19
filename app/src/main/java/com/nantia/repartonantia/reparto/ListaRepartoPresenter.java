package com.nantia.repartonantia.reparto;

import android.os.AsyncTask;
import android.view.View;

import com.google.gson.Gson;
import com.nantia.repartonantia.adapters.RepartoInfoPOJO;
import com.nantia.repartonantia.data.AppDatabase;
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
    private AppDatabase db;

    ListaRepartoPresenter(ListaRepartoView view, AppDatabase db) {
        this.view = view;
        this.db = db;
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
                    view.showError("No hay repartos creados!!");
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
                    response.body().getStock().setActualizado(true);
                    DataHolder.setReparto(response.body());
                    guardarReparto(response.body());
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

    private void guardarReparto(final Reparto reparto){
        DataHolder.setReparto(reparto);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.repartoDao().nukeTable();
                db.repartoDao().insertAll(reparto);
                db.vehiculoDao().nukeTable();
                db.vehiculoDao().insertAll(reparto.getVehiculo());
                db.stockDao().nukeTable();
                db.stockDao().insertAll(reparto.getStock());
                db.usuarioDao().nukeTable();
                db.usuarioDao().insertAll(reparto.getVendedor1(), reparto.getVendedor2());
                db.rutaDao().nukeTable();
                db.rutaDao().insertAll(reparto.getRuta());
            }
        });
    }

}
