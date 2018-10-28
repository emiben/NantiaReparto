package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Emi on 23/5/2018.
 */

@Entity
public class Cliente implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_pk")
    private transient long localPK;

    @ColumnInfo(name = "cliente_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "nro_documento")
    @SerializedName("nroDocumento")
    private String nroDocumento;

    @ColumnInfo(name = "tipo_documento")
    @SerializedName("tipoDocumento")
    private String tipoDocumento;

    @ColumnInfo(name = "nombre_1")
    @SerializedName("nombre1")
    private String nombre1;

    @ColumnInfo(name = "nombre_2")
    @SerializedName("nombre2")
    private String nombre2;

    @ColumnInfo(name = "saldo")
    @SerializedName("saldo")
    private float saldo;

    @ColumnInfo(name = "dif_saldo")
    @SerializedName("difSaldo")
    private float difSaldo;

    @ColumnInfo(name = "fecha_nacimiento")
    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @ColumnInfo(name = "fecha_alta")
    @SerializedName("fechaAlta")
    private String fechaAlta;

    @ColumnInfo(name = "mail")
    @SerializedName("mail")
    private String mail;

    @ColumnInfo(name = "celular")
    @SerializedName("celular")
    private String celular;

    @ColumnInfo(name = "id_lista")
    @SerializedName("idLista")
    private long idLista;

    @ColumnInfo(name = "observaciones")
    @SerializedName("observaciones")
    private String observaciones;

    @ColumnInfo(name = "activo")
    @SerializedName("activo")
    private boolean activo;

    @Embedded
    @SerializedName("direccion")
    private Direccion direccion;

    @SerializedName("setEnvasesEnPrestamo")
    @TypeConverters(EnvaseEnPrestamoTypeConverter.class)
    private List<EnvaseEnPrestamo> envasesEnPrestamo;

    @SerializedName("dias")
    @TypeConverters(DiaTypeConverter.class)
    private List<Dia> dias;

    @ColumnInfo(name = "actualizado_cliente")
    private transient boolean actualizado;

    @ColumnInfo(name = "visitado")
    private transient boolean visitado = false;

    public Cliente(){

    }

    //@Ignore
    public Cliente(long id, String nroDocumento, String tipoDocumento,
                   String nombre1, String nombre2, float saldo, float difSaldo, String fechaNacimiento,
                   String fechaAlta, String mail, String celular, long idLista, String observaciones,
                   boolean activo, Direccion direccion, List<EnvaseEnPrestamo> envasesEnPrestamo,
                   List<Dia> dias) {
        this.id = id;
        this.nroDocumento = nroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.saldo = saldo;
        this.difSaldo = difSaldo;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
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

    public float getDifSaldo() {
        return difSaldo;
    }

    public void setDifSaldo(float difSaldo) {
        this.difSaldo = difSaldo;
    }

    public List<EnvaseEnPrestamo> getEnvasesEnPrestamo() {
        return envasesEnPrestamo;
    }

    public void setEnvasesEnPrestamo(List<EnvaseEnPrestamo> envasesEnPrestamo) {
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

    public long getIdLista() {
        return idLista;
    }

    public void setIdLista(long idLista) {
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

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

    public boolean isActualizado() {
        return actualizado;
    }

    public void setActualizado(boolean actualizado) {
        this.actualizado = actualizado;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public String getNombreCompleto(){
        return nombre1 + " " + nombre2;
    }
}
