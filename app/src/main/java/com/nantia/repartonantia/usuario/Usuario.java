package com.nantia.repartonantia.usuario;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Emi on 7/5/2018.
 */

@Entity
public class Usuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usuario_pk")
    private transient long localPK;

    @ColumnInfo(name = "usuario_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "nombre_usuario")
    @SerializedName("usuario")
    private String nombreUsuario;

    @ColumnInfo(name = "nombre")
    @SerializedName("nombre")
    private String nombre;

    @ColumnInfo(name = "apellido")
    @SerializedName("apellido")
    private String apellido;

    @Embedded
    @SerializedName("rol")
    private Rol rol;

    @ColumnInfo(name = "usuario_contrasenia")
    @SerializedName("contrasenia")
    private String contrasenia;

    @ColumnInfo(name = "es_vendedor")
    @SerializedName("esVendedor")
    private boolean esVendedor;

    @ColumnInfo(name = "usuario_saldo_caja")
    @SerializedName("saldoCaja")
    private float saldoCaja;

    @ColumnInfo(name = "actualizado")
    private transient boolean actualizado;

    public Usuario(long id, String nombreUsuario, String nombre, String apellido, Rol rol, String contrasenia, boolean esVendedor, float saldoCaja) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.esVendedor = esVendedor;
        this.saldoCaja = saldoCaja;
    }

    public long getLocalPK() {
        return localPK;
    }

    public void setLocalPK(long localPK) {
        this.localPK = localPK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public boolean isActualizado() {
        return actualizado;
    }

    public void setActualizado(boolean actualizado) {
        this.actualizado = actualizado;
    }
}
