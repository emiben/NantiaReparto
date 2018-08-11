package com.nantia.repartonantia.usuario;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Emi on 7/5/2018.
 */

public class Usuario {

    @SerializedName("id")
    private long id;

    @SerializedName("usuario")
    private String usuario;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("rol")
    private Rol rol;

    @SerializedName("contrasenia")
    private String contrasenia;

    @SerializedName("esVendedor")
    private boolean esVendedor;

    @SerializedName("saldoCaja")
    private float saldoCaja;

    public Usuario(long id, String usuario, String nombre, String apellido, Rol rol, String contrasenia, boolean esVendedor, float saldoCaja) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.esVendedor = esVendedor;
        this.saldoCaja = saldoCaja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEsVendedor() {
        return esVendedor;
    }

    public void setEsVendedor(boolean esVendedor) {
        this.esVendedor = esVendedor;
    }

    public float getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(float saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public String getNombreCompleto(){
        return nombre + " " + apellido;
    }
}
