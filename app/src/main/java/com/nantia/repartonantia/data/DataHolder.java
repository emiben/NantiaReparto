package com.nantia.repartonantia.data;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.usuario.Usuario;

import java.util.ArrayList;

/**
 * Created by Emi on 24/6/2018.
 */

public class DataHolder {
    private static Usuario usuario;
    private static ArrayList<Envase> envases;
    private static ArrayList<Producto> productos;
    private static ArrayList<Cliente> clientes;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        DataHolder.usuario = usuario;
    }

    public static ArrayList<Envase> getEnvases() {
        return envases;
    }

    public static void setEnvases(ArrayList<Envase> envases) {
        DataHolder.envases = envases;
    }

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    public static void setProductos(ArrayList<Producto> productos) {
        DataHolder.productos = productos;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        DataHolder.clientes = clientes;
    }
}
