package com.nantia.repartonantia.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Emi on 7/5/2018.
 */

public class RetrofitClientInstance {
    private static Retrofit retrofit;
//    private static final String BASE_URL = "https://nantia-servidor.herokuapp.com/api/";
    private static final String BASE_URL = "https://nantia-servidor-test.herokuapp.com/api/";
    //private static final String BASE_URL = "http://localhost:8080/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
