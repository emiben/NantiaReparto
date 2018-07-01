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

    @SerializedName("nroDocumento")
    private String nroDocumento;

    @SerializedName("tipoDocumento")
    private TipoDocumento tipoDocumento;

    @SerializedName("nombre1")
    private String nombre1;

    @SerializedName("nombre2")
    private String nombre2;

    @SerializedName("saldo")
    private float saldo;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("fechaAlta")
    private String fechaAlta;

    @SerializedName("mail")
    private String mail;

    @SerializedName("celular")
    private String celular;

    @SerializedName("idLista")
    private String idLista;

    @SerializedName("observaciones")
    private String observaciones;

    @SerializedName("activo")
    private boolean activo;

    @SerializedName("direccion")
    private Direccion direccion;

    @SerializedName("setEnvasesEnPrestamo")
    private ArrayList<EnvaseEnPrestamo> envasesEnPrestamo;

    @SerializedName("dias")
    private ArrayList<Dia> dias;

    public Cliente(){

    }

    public Cliente(long id, String nroDocumento, TipoDocumento tipoDocumento,
                   String nombre1, String nombre2, float saldo, String fechaNacimiento,
                   String fechaAlta, String mail, String celular, String idLista, String observaciones,
                   boolean activo, Direccion direccion, ArrayList<EnvaseEnPrestamo> envasesEnPrestamo,
                   ArrayList<Dia> dias) {
        this.id = id;
        this.nroDocumento = nroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.saldo = saldo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.mail = mail;
        this.celular = celular;
        this.idLista = idLista;
        this.observaciones = observaciones;
        this.activo = activo;
        this.direccion = direccion;
        this.envasesEnPrestamo = envasesEnPrestamo;
        this.dias = dias;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
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

    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }
}
