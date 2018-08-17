package com.nantia.repartonantia.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.cliente.ClienteDao;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.EnvaseDao;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.producto.ProductoDao;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.usuario.UsuarioDao;

@Database(entities = {Usuario.class, Cliente.class,
          Envase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public abstract UsuarioDao usuarioDao();

  public abstract ClienteDao clienteDao();

  public abstract EnvaseDao envaseDao();

  public abstract ProductoDao productoDao();

}
