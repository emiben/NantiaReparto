package com.nantia.repartonantia.cliente;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Emi on 23/5/2018.
 */

public class Cliente implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("direccion")
    private Direccion direccion;

    @SerializedName("tipoDocumento")
    private TipoDocumento tipoDocumento;

    @SerializedName("nroDocumento")
    private String nroDocumento;

    @SerializedName("nombre1")
    private String nombre1;

    @SerializedName("nombre2")
    private String nombre2;

    @SerializedName("saldo")
    private float saldo;

    @SerializedName("envaseEnPrestamo")
    private ArrayList<EnvaseEnPrestamo> envasesEnPrestamo;

    @SerializedName("fechaNacimiento")
    private Date fechaNacimiento;

    @SerializedName("fechaAlta")
    private Date fechaAlta;

    @SerializedName("celular")
    private int celular;

    @SerializedName("mail")
    private String mail;

    @SerializedName("idLista")
    private int idLista;

    @SerializedName("observaciones")
    private String observaciones;

    @SerializedName("activo")
    private boolean activo;

    public Cliente(long id, Direccion direccion, TipoDocumento tipoDocumento, String nroDocumento,
                   String nombre1, String nombre2, float saldo, ArrayList<EnvaseEnPrestamo> envasesEnPrestamo,
                   Date fechaNacimiento, Date fechaAlta, int celular, String mail, int idLista,
                   String observaciones, boolean activo) {
        this.id = id;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.saldo = saldo;
        this.envasesEnPrestamo = envasesEnPrestamo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.celular = celular;
        this.mail = mail;
        this.idLista = idLista;
        this.observaciones = observaciones;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public ArrayList<EnvaseEnPrestamo> getEnvasesEnPrestamo() {
        return envasesEnPrestamo;
    }

    public void setEnvasesEnPrestamo(ArrayList<EnvaseEnPrestamo> envasesEnPrestamo) {
        this.envasesEnPrestamo = envasesEnPrestamo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
