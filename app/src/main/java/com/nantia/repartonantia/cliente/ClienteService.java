package com.nantia.repartonantia.cliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Emi on 23/6/2018.
 */

public interface ClienteService {

    @GET("clientes")
    Call<ArrayList<Cliente>> getClientes();
}
