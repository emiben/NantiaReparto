package com.nantia.repartonantia.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.cliente.ClienteDao;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ListaDePrecioDao;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.EnvaseDao;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.producto.ProductoDao;
import com.nantia.repartonantia.reparto.Reparto;
import com.nantia.repartonantia.reparto.RepartoDao;
import com.nantia.repartonantia.reparto.Vehiculo;
import com.nantia.repartonantia.reparto.VehiculoDao;
import com.nantia.repartonantia.stock.Stock;
import com.nantia.repartonantia.stock.StockDao;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.usuario.UsuarioDao;
import com.nantia.repartonantia.venta.Venta;
import com.nantia.repartonantia.venta.VentaDao;

@Database(entities = {Usuario.class, Cliente.class,
          Envase.class, Producto.class, Stock.class,
          ListaDePrecio.class, Reparto.class, Venta.class,
          Vehiculo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();

    public abstract ClienteDao clienteDao();

    public abstract EnvaseDao envaseDao();

    public abstract ProductoDao productoDao();

    public abstract RepartoDao repartoDao();

    public abstract VehiculoDao vehiculoDao();

    public abstract StockDao stockDao();

    public abstract ListaDePrecioDao listaDePrecioDao();

    public abstract VentaDao ventaDao();


}
