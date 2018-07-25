package com.nantia.repartonantia.stock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StockService {

  @GET("stock/{id}")
  Call<Stock> getStock(@Path("id") long id);

  @PUT("stock/{id}")
  @Headers({
      "Content-Type: application/json;charset=utf-8",
      "Accept: application/json;charset=utf-8"})
  Call<Stock> updateStock(@Path("id") long id, @Body Stock stock);
}
