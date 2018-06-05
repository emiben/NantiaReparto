package com.nantia.repartonantia.producto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Emi on 21/5/2018.
 */

public interface ProductoService {

    @GET("productos")
    Call<ArrayList<Producto>> getProductos();
}
