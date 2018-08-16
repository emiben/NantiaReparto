package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface ClienteDao {

  @Query("SELECT * FROM cliente")
  List<Cliente> getAll();

  @Query("SELECT * FROM cliente WHERE id IN (:ids)")
  List<Cliente> loadAllByIds(int[] ids);

  @Query("SELECT * FROM cliente WHERE nombre_1 LIKE :nombre AND "
      + "nombre_2 LIKE :apellido LIMIT 1")
  Cliente findByName(String nombre, String apellido);

  @Query("SELECT * FROM cliente WHERE actualizado = 0")
  List<Cliente> getClienteNoActualizado();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(Cliente... clientes);

  @Delete
  void delete(Cliente cliente);

}
