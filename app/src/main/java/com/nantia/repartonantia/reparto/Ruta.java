package com.nantia.repartonantia.reparto;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.cliente.Dia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Emi on 28/7/2018.
 */

public class Ruta implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("dia")
    private Dia dia;

    @SerializedName("setRutaCliente")
    private ArrayList<RutaCliente> rutaClientes;

    public Ruta(long id, String nombre, Dia dia, ArrayList<RutaCliente> rutaClientes) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.rutaClientes = rutaClientes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public ArrayList<RutaCliente> getRutaClientes() {
        return rutaClientes;
    }

    public void setRutaClientes(ArrayList<RutaCliente> rutaClientes) {
        this.rutaClientes = rutaClientes;
    }
}
