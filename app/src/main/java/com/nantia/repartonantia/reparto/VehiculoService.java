package com.nantia.repartonantia.reparto;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VehiculoService {

    @PUT("vehiculo/{id}")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json;charset=utf-8"})
    Call<Vehiculo> updateVehiculo(@Path("id") long id, @Body Vehiculo vehiculo);
}
