package com.nantia.repartonantia.venta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VentaService {

    @POST("dataventas")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8"})
    Call<Venta> saveVenta(@Body Venta cliente);
}
