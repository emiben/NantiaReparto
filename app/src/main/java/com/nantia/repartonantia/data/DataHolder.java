package com.nantia.repartonantia.data;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.reparto.Reparto;
import com.nantia.repartonantia.reparto.Ruta;
import com.nantia.repartonantia.stock.Stock;
import com.nantia.repartonantia.usuario.Usuario;

import com.nantia.repartonantia.venta.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 24/6/2018.
 */

public class DataHolder {
    private static Usuario usuario;
    private static ArrayList<Envase> envases;
    private static ArrayList<Producto> productos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<ListaDePrecio> listasDePrecios;
//    private static Stock stock;
    private static Reparto reparto;
    private static List<Venta> ventas;
    private static Ruta ruta;

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

    public static ArrayList<ListaDePrecio> getListasDePrecios() {
        return listasDePrecios;
    }

    public static void setListasDePrecios(ArrayList<ListaDePrecio> listasDePrecios) {
        DataHolder.listasDePrecios = listasDePrecios;
    }

//    public static Stock getStock() {
//        return stock;
//    }

//    public static void setStock(Stock stock) {
//        DataHolder.stock = stock;
//    }

    public static Reparto getReparto() {
        return reparto;
    }

    public static void setReparto(Reparto reparto) {
        DataHolder.reparto = reparto;
    }

    public static List<Venta> getVentas() {
        return ventas;
    }

    public static void setVentas(List<Venta> ventas) {
        DataHolder.ventas = ventas;
    }

    public static Ruta getRuta() {
        return ruta;
    }

    public static void setRuta(Ruta ruta) {
        DataHolder.ruta = ruta;
    }

    public static ListaDePrecio getListaDePrecioById(long id){
        for (ListaDePrecio listaDePrecio : listasDePrecios){
            if(listaDePrecio.getId() == id){
                return listaDePrecio;
            }
        }
        return null;
    }
}