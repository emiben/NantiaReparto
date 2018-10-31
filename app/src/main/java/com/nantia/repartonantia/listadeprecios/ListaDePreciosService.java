package com.nantia.repartonantia.listadeprecios;

import com.nantia.repartonantia.listadeprecios.ListaDePrecio;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Emi on 5/7/2018.
 */

public interface ListaDePreciosService {

    @GET("listaprecio")
    Call<ArrayList<ListaDePrecio>> getListasDePrecios();
}
