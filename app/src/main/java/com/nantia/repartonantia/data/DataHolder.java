package com.nantia.repartonantia.data;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.reparto.Reparto;
import com.nantia.repartonantia.reparto.Ruta;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.venta.Venta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 24/6/2018.
 */

public class DataHolder {
    private static Usuario usuario;
    private static List<Envase> envases;
    private static List<Producto> productos;
    private static List<Cliente> clientes;
    private static List<ListaDePrecio> listasDePrecios;
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

    public static List<Envase> getEnvases() {
        return envases;
    }

    public static void setEnvases(List<Envase> envases) {
        DataHolder.envases = envases;
    }

    public static List<Producto> getProductos() {
        return productos;
    }

    public static void setProductos(List<Producto> productos) {
        DataHolder.productos = productos;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(List<Cliente> clientes) {
        DataHolder.clientes = clientes;
    }

    public static List<ListaDePrecio> getListasDePrecios() {
        return listasDePrecios;
    }

    public static void setListasDePrecios(List<ListaDePrecio> listasDePrecios) {
        DataHolder.listasDePrecios = listasDePrecios;
    }

    //TODO: Fix bug que agrega como lista de precios la lista ID 0 "Elegir lista de precios"
    public static List<ListaDePrecio> removeListaCero(){
        boolean encontre = false;
        for(int i = 0; i < listasDePrecios.size() && !encontre; i++){
            ListaDePrecio listaDePrecio = listasDePrecios.get(i);
            if(listaDePrecio.getId() == 0){
                listasDePrecios.remove(listaDePrecio);
                encontre = true;
            }
        }
        return listasDePrecios;
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

    public static void removeClienteById(long clineteId){
        Boolean encontre = false;
        for (int i = 0; i < clientes.size() && !encontre; i++){
            if(clientes.get(i).getId() == clineteId){
                clientes.remove(i);
                encontre = true;
            }
        }
    }

    public static void updateClienteEnReparto(Cliente cliente){
        if(reparto != null && reparto.getRuta() != null && reparto.getRuta().getClientes() != null){
            List<Cliente> clientes = reparto.getRuta().getClientes();
            Boolean encontre = false;
            for (int i = 0; i < clientes.size() && !encontre; i++){
                if(clientes.get(i).getId() == cliente.getId()){
                    reparto.getRuta().updateCliente(cliente);
                    encontre = true;
                }
            }
        }
    }

    public static List<Venta> getVentasSinEnviar(){
        List<Venta> mVentas = new ArrayList<>();
        if(ventas != null){
            for (Venta venta : ventas){
                if(!venta.isActualizado()){
                    mVentas.add(venta);
                }
            }
        }
        return mVentas;
    }

    public static Cliente getClienteById(long id){
        Cliente cliente = null;
        for(Cliente clienteTemp : clientes){
            if(clienteTemp.getId() == id){
                cliente = clienteTemp;
            }
        }
        return cliente;
    }
}