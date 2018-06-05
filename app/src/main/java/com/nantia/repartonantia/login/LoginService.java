package com.nantia.repartonantia.login;

import com.nantia.repartonantia.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Emi on 7/5/2018.
 */

public interface LoginService {

    @POST("login")
    Call<Usuario> login(@Body Login login);
}
