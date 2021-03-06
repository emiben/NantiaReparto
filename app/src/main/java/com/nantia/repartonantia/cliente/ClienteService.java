package com.nantia.repartonantia.cliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Emi on 23/6/2018.
 */

public interface ClienteService {

    @GET("clientes")
    Call<ArrayList<Cliente>> getClientes();

    @POST("clientes")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8"})
    Call<Cliente> saveCliente(@Body Cliente cliente);

    @PUT("clientes/{id}")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8"})
    Call<Cliente> updateCliente(@Path("id") long id, @Body Cliente cliente);
}
