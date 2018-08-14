package com.nantia.repartonantia.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.usuario.UsuarioDao;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  public abstract UsuarioDao usuarioDao();

}
