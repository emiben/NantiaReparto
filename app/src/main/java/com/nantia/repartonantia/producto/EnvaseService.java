package com.nantia.repartonantia.producto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Emi on 24/6/2018.
 */

public interface EnvaseService {

    @GET("envasesTipo")
    Call<ArrayList<Envase>> getEnvases();
}
