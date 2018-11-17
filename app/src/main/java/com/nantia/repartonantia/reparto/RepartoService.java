package com.nantia.repartonantia.reparto;

import com.nantia.repartonantia.adapters.RepartoInfoPOJO;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Emi on 4/8/2018.
 */

public interface RepartoService {

    @GET("datareparto")
    Call<ArrayList<RepartoInfoPOJO>> getRepartosInfo();

    @GET("reparto/{id}")
    Call<Reparto> getReparto(@Path("id") long id);

    @PUT("updateEstadoReparto/{id}/{estado}")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8"})
    Call<ResponseBody> updateEstadoReparto(@Path("id") long id, @Path("estado") String estado);
}

