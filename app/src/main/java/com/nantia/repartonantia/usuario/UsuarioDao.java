package com.nantia.repartonantia.usuario;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface UsuarioDao {

  @Query("SELECT * FROM usuario")
  List<Usuario> getAll();

  @Query("SELECT * FROM usuario WHERE usuario_id IN (:ids)")
  List<Usuario> loadAllByIds(int[] ids);

  @Query("SELECT * FROM usuario WHERE nombre LIKE :nombre AND "
      + "apellido LIKE :apellido LIMIT 1")
  Usuario findByName(String nombre, String apellido);

  @Query("SELECT * FROM usuario WHERE actualizado = 0")
  List<Usuario> getUsuarioNoActualizado();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(Usuario... usuarios);

  @Delete
  void delete(Usuario usuario);

  @Query("DELETE FROM usuario")
  void nukeTable();

}
