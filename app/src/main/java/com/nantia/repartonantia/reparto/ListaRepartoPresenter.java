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

        //TODO: cambiar a la llamada cuando el fenomeno termine
        ArrayList<RepartoInfoPOJO> repartos = new ArrayList<>();
        repartos.add(new RepartoInfoPOJO(1, "Rep 1", "Vehiculo 1", "Martes"));
        repartos.add(new RepartoInfoPOJO(2, "Rep 2", "Vehiculo 2", "Miercoles"));
        repartos.add(new RepartoInfoPOJO(3, "Rep 3", "Vehiculo 3", "Jueves"));

        view.setRepartosInfo(repartos);
        view.addListeners();
        view.onSetProgressBarVisibility(View.GONE);

//        RepartoService service = RetrofitClientInstance.getRetrofitInstance().create(RepartoService.class);
//        Call<ArrayList<RepartoInfoPOJO>> call = service.getRepartosInfo();
//        call.enqueue(new Callback<ArrayList<RepartoInfoPOJO>>() {
//            @Override
//            public void onResponse(Call<ArrayList<RepartoInfoPOJO>> call, Response<ArrayList<RepartoInfoPOJO>> response) {
//                if(response.body() != null){
//                    view.setRepartosInfo(response.body());
//                    view.addListeners();
//                    view.onSetProgressBarVisibility(View.GONE);
//                }else {
//                    view.onSetProgressBarVisibility(View.GONE);
//                    //TODO: implementar el manejo de errores
//                    view.showError("implementar el manejo de errores getRepartosInfo");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<RepartoInfoPOJO>> call, Throwable t) {
//                view.onSetProgressBarVisibility(View.GONE);
//                view.showError(t.getMessage());
//            }
//        });
    }

    void getReparto(final long repartoId){
        view.onSetProgressBarVisibility(View.VISIBLE);

        //TODO: cambiar a la llamada cuando el fenomeno termine
        Reparto reparto = new Gson().fromJson(repartoString, Reparto.class);
        reparto.getVehiculo().getStock().setActualizado(true);
        guardarReparto(reparto);
        view.onSetProgressBarVisibility(View.GONE);
        view.navigateToReparto(reparto);

//        RepartoService service = RetrofitClientInstance.getRetrofitInstance().create(RepartoService.class);
//        Call<Reparto> call = service.getReparto(repartoId);
//        call.enqueue(new Callback<Reparto>() {
//            @Override
//            public void onResponse(Call<Reparto> call, Response<Reparto> response) {
//                if(response.body() != null){
//                    view.onSetProgressBarVisibility(View.GONE);
//                    response.body().getVehiculo().getStock().setActualizado(true);
//                    DataHolder.setReparto(response.body());
//                    guardarReparto(response.body());
//                    view.navigateToReparto(response.body());
//                }
//                else {
//                    view.onSetProgressBarVisibility(View.GONE);
//                    //TODO: implementar el manejo de errores
//                    view.showError("implementar el manejo de errores getReparto");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Reparto> call, Throwable t) {
//                view.onSetProgressBarVisibility(View.GONE);
//                view.showError(t.getMessage());
//            }
//        });
    }

    private void guardarReparto(final Reparto reparto){
        DataHolder.setReparto(reparto);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.repartoDao().insertAll(reparto);
                db.vehiculoDao().insertAll(reparto.getVehiculo());
                db.stockDao().insertAll(reparto.getVehiculo().getStock());
                db.usuarioDao().insertAll(reparto.getVendedor1(), reparto.getVendedor2());
            }
        });
    }


    private String repartoString = "{\n" +
            "\t\"descripcion\": \"Reparto Martes Zona Este\",\n" +
            "\t\"vendedor1\": {\n" +
            "\t\t\"id\": 5,\n" +
            "\t\t\"usuario\": \"usuario1\",\n" +
            "\t\t\"nombre\": \"Usuario\",\n" +
            "\t\t\"apellido\": \"Uno\",\n" +
            "\t\t\"rol\": {\n" +
            "\t\t\t\"id\": 1,\n" +
            "\t\t\t\"nombreRol\": \"Administrador\"\n" +
            "\t\t},\n" +
            "\t\t\"contrasenia\": \"usuario1\",\n" +
            "\t\t\"esVendedor\": true,\n" +
            "\t\t\"saldoCaja\": 500\n" +
            "\t},\n" +
            "\t\"vendedor2\": {\n" +
            "\t\t\"id\": 6,\n" +
            "\t\t\"usuario\": \"usuario2\",\n" +
            "\t\t\"nombre\": \"Usuario\",\n" +
            "\t\t\"apellido\": \"Dos\",\n" +
            "\t\t\"rol\": {\n" +
            "\t\t\t\"id\": 2,\n" +
            "\t\t\t\"nombreRol\": \"Vendedor\"\n" +
            "\t\t},\n" +
            "\t\t\"contrasenia\": \"usuario2\",\n" +
            "\t\t\"esVendedor\": true,\n" +
            "\t\t\"saldoCaja\": 300\n" +
            "\t},\n" +
            "\t\"vehiculo\": {\n" +
            "\t\t\"id\": 72,\n" +
            "\t\t\"matricula\": \"AAA-212\",\n" +
            "\t\t\"marca\": \"Fiat\",\n" +
            "\t\t\"modelo\": \"Fiorino\",\n" +
            "\t\t\"descripcion\": \"Camioneta\",\n" +
            "\t\t\"activo\": true,\n" +
            "\t\t\"stock\": {\n" +
            "\t\t\t\"id\": 0,\n" +
            "\t\t\t\"fecha\": \"2017-12-08 00:00:00\",\n" +
            "\t\t\t\"setEnvaseStock\": [{\n" +
            "\t\t\t\t\t\"id\": 25,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"envasesTipos\": {\n" +
            "\t\t\t\t\t\t\"id\": 9,\n" +
            "\t\t\t\t\t\t\"descripcion\": \"Damajuana 3L\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": 24,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"envasesTipos\": {\n" +
            "\t\t\t\t\t\t\"id\": 10,\n" +
            "\t\t\t\t\t\t\"descripcion\": \"Dispensador Común\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": 23,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"envasesTipos\": {\n" +
            "\t\t\t\t\t\t\"id\": 8,\n" +
            "\t\t\t\t\t\t\"descripcion\": \"Sifón 3L\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t],\n" +
            "\t\t\t\"setProductoStock\": [{\n" +
            "\t\t\t\t\t\"id\": 22,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"producto\": {\n" +
            "                        \"productoId\": 112,\n" +
            "                        \"nombre\": \"Agua Con Gas\",\n" +
            "                        \"presentacion\": \"2l\",\n" +
            "                        \"descripcion\": \"Agua Con Gas 2l\",\n" +
            "                        \"retornable\": false,\n" +
            "                        \"envasesTipos\": null\n" +
            "                    }\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": 21,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"producto\": {\n" +
            "                        \"productoId\": 111,\n" +
            "                        \"nombre\": \"Jugo de pomelo\",\n" +
            "                        \"presentacion\": \"2l\",\n" +
            "                        \"descripcion\": \"Jugo de Pomelo 2l\",\n" +
            "                        \"retornable\": false,\n" +
            "                        \"envasesTipos\": null\n" +
            "                    }\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"id\": 20,\n" +
            "\t\t\t\t\t\"cantidad\": 100,\n" +
            "\t\t\t\t\t\"fecha\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"producto\": {\n" +
            "                        \"productoId\": 110,\n" +
            "                        \"nombre\": \"Agua Sin Gas\",\n" +
            "                        \"presentacion\": \"2l\",\n" +
            "                        \"descripcion\": \"Agua Sin Gas 2l\",\n" +
            "                        \"retornable\": true,\n" +
            "                        \"envasesTipos\": {\n" +
            "                            \"id\": 8,\n" +
            "                            \"descripcion\": \"Sifón 3L\"\n" +
            "                        }\n" +
            "                    }\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t},\n" +
            "\t\"fecha\": \"2018-09-18 00:00:00\",\n" +
            "\t\"ruta\": {\n" +
            "\t\t\"id\": 88,\n" +
            "\t\t\"nombre\": \"Ruta Este\",\n" +
            "\t\t\"dias\": \"MARTES\",\n" +
            "\t\t\"setRutaCliente\": [{\n" +
            "\t\t\t\t\"cliente\": {\n" +
            "\t\t\t\t\t\"id\": 19,\n" +
            "\t\t\t\t\t\"direccion\": {\n" +
            "\t\t\t\t\t\t\"id\": 20,\n" +
            "\t\t\t\t\t\t\"direccion\": \"Av Libertador 1960\",\n" +
            "\t\t\t\t\t\t\"coordLon\": \"-56.39424198207337\",\n" +
            "\t\t\t\t\t\t\"coordLat\": \"-34.457037829355386\",\n" +
            "\t\t\t\t\t\t\"telefono\": \"29240559\",\n" +
            "\t\t\t\t\t\t\"ciudad\": \"Santa Lucia\",\n" +
            "\t\t\t\t\t\t\"departamento\": \"Canelones\",\n" +
            "\t\t\t\t\t\t\"codPostal\": \"11200\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"tipoDocumento\": \"RUT\",\n" +
            "\t\t\t\t\t\"nroDocumento\": \"22229977\",\n" +
            "\t\t\t\t\t\"nombre1\": \"Park\",\n" +
            "\t\t\t\t\t\"nombre2\": \"Aguada\",\n" +
            "\t\t\t\t\t\"saldo\": 1999,\n" +
            "\t\t\t\t\t\"fechaNacimiento\": \"2000-04-01 00:00:00\",\n" +
            "\t\t\t\t\t\"fechaAlta\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"celular\": \"0994262\",\n" +
            "\t\t\t\t\t\"mail\": \"aguadaPark@gmail.com\",\n" +
            "\t\t\t\t\t\"idLista\": 0,\n" +
            "\t\t\t\t\t\"observaciones\": null,\n" +
            "\t\t\t\t\t\"activo\": true,\n" +
            "\t\t\t\t\t\"dias\": [],\n" +
            "\t\t\t\t\t\"setEnvasesEnPrestamo\": [{\n" +
            "\t\t\t\t\t\t\t\"id\": 22,\n" +
            "\t\t\t\t\t\t\t\"envasetipos\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": 9,\n" +
            "\t\t\t\t\t\t\t\t\"descripcion\": \"Damajuana 3L\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"cantidad\": 2\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\"id\": 23,\n" +
            "\t\t\t\t\t\t\t\"envasetipos\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": 8,\n" +
            "\t\t\t\t\t\t\t\t\"descripcion\": \"Sifón 3L\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"cantidad\": 5\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\"id\": 21,\n" +
            "\t\t\t\t\t\t\t\"envasetipos\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": 10,\n" +
            "\t\t\t\t\t\t\t\t\"descripcion\": \"Dispensador Común\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"cantidad\": 3\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"ordenVisita\": 1\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"cliente\": {\n" +
            "\t\t\t\t\t\"id\": 24,\n" +
            "\t\t\t\t\t\"direccion\": {\n" +
            "\t\t\t\t\t\t\"id\": 25,\n" +
            "\t\t\t\t\t\t\"direccion\": \"Paraguay 2141, 1602\",\n" +
            "\t\t\t\t\t\t\"coordLon\": \"-56.388466176577595\",\n" +
            "\t\t\t\t\t\t\"coordLat\": \"-34.456476337049345\",\n" +
            "\t\t\t\t\t\t\"telefono\": \"22008333\",\n" +
            "\t\t\t\t\t\t\"ciudad\": \"Santa Lucia\",\n" +
            "\t\t\t\t\t\t\"departamento\": \"Canelones\",\n" +
            "\t\t\t\t\t\t\"codPostal\": \"11800\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"tipoDocumento\": \"CI\",\n" +
            "\t\t\t\t\t\"nroDocumento\": \"49013921\",\n" +
            "\t\t\t\t\t\"nombre1\": \"Haiache\",\n" +
            "\t\t\t\t\t\"nombre2\": \"Dayana\",\n" +
            "\t\t\t\t\t\"saldo\": 0,\n" +
            "\t\t\t\t\t\"fechaNacimiento\": \"1987-10-05 00:00:00\",\n" +
            "\t\t\t\t\t\"fechaAlta\": \"2018-08-25 00:00:00\",\n" +
            "\t\t\t\t\t\"celular\": \"094250653\",\n" +
            "\t\t\t\t\t\"mail\": \"Dayana.haiache@gmail.com\",\n" +
            "\t\t\t\t\t\"idLista\": 0,\n" +
            "\t\t\t\t\t\"observaciones\": null,\n" +
            "\t\t\t\t\t\"activo\": true,\n" +
            "\t\t\t\t\t\"dias\": [],\n" +
            "\t\t\t\t\t\"setEnvasesEnPrestamo\": [{\n" +
            "\t\t\t\t\t\t\t\"id\": 26,\n" +
            "\t\t\t\t\t\t\t\"envasetipos\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": 9,\n" +
            "\t\t\t\t\t\t\t\t\"descripcion\": \"Damajuana 3L\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"cantidad\": 6\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\"id\": 27,\n" +
            "\t\t\t\t\t\t\t\"envasetipos\": {\n" +
            "\t\t\t\t\t\t\t\t\"id\": 8,\n" +
            "\t\t\t\t\t\t\t\t\"descripcion\": \"Sifón 3L\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"cantidad\": 4\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"ordenVisita\": 1\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t\"estado\": \"CREADO\",\n" +
            "\t\"fabricaid\": 64\n" +
            "}";
}
