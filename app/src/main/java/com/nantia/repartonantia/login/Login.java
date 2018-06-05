package com.nantia.repartonantia.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Emi on 6/5/2018.
 */

public class Login implements ILogin {

    @SerializedName("nombreUsuario")
    private String nombreUsuario;

    @SerializedName("contrasenia")
    private String contrasenia;

    public Login(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String getContrasenia() {
        return contrasenia;
    }
}
