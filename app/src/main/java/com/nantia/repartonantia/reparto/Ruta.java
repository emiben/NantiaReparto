package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.text.BoringLayout;

import com.google.gson.annotations.SerializedName;
import com.nantia.repartonantia.cliente.Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 28/7/2018.
 */

@Entity
public class Ruta implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "ruta_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "nombre")
    @SerializedName("nombre")
    private String nombre;

    @ColumnInfo(name = "dia")
    @SerializedName("dia")
    private String dia;

    @SerializedName("setRutaCliente")
    @TypeConverters(RutaClienteTypeConverter.class)
    private List<RutaCliente> rutaClientes;

//    public Ruta(long id, String nombre, String dia, ArrayList<RutaCliente> rutaClientes) {
//        this.id = id;
//        this.nombre = nombre;
//        this.dia = dia;
//        this.rutaClientes = rutaClientes;
//    }

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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public List<RutaCliente> getRutaClientes() {
        return rutaClientes;
    }

    public void setRutaClientes(List<RutaCliente> rutaClientes) {
        this.rutaClientes = rutaClientes;
    }

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (RutaCliente rutaCliente : rutaClientes){
            clientes.add(rutaCliente.getCliente());
        }
        return clientes;
    }


    public List<Cliente> getClientesSinVisitar(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (RutaCliente rutaCliente : rutaClientes){
            if(!rutaCliente.getCliente().isVisitado()){
                clientes.add(rutaCliente.getCliente());
            }
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente){
        Boolean encontre = false;
        for (int i = 0; i < rutaClientes.size() && !encontre; i++){
            if(rutaClientes.get(i).getCliente().getId() == cliente.getId()){
                rutaClientes.get(i).setCliente(cliente);
                encontre = true;
            }
        }
    }

}
