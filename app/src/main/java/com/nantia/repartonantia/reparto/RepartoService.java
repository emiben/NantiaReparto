package com.nantia.repartonantia.reparto;

import com.nantia.repartonantia.adapters.RepartoInfoPOJO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Emi on 4/8/2018.
 */

public interface RepartoService {

    @GET("datareparto")
    Call<ArrayList<RepartoInfoPOJO>> getRepartosInfo();

    @GET("reparto/{id}")
    Call<Reparto> getReparto(@Path("id") long id);
}
