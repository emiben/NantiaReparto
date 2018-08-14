package com.nantia.repartonantia.usuario;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.ArrayList;

@Dao
public interface UsuarioDao {

  @Query("SELECT * FROM usuario")
  ArrayList<Usuario> getAll();

  @Query("SELECT * FROM usuario WHERE id IN (:ids)")
  ArrayList<Usuario> loadAllByIds(int[] ids);

  @Query("SELECT * FROM usuario WHERE nombre LIKE :nombre AND "
      + "apellido LIKE :apellido LIMIT 1")
  Usuario findByName(String nombre, String apellido);

  @Query("SELECT * FROM usuario WHERE actualizado = 0")
  ArrayList<Usuario> getUsuarioNoActualizado();

  @Insert
  void insertAll(Usuario... usuarios);

  @Delete
  void delete(Usuario usuario);

}
