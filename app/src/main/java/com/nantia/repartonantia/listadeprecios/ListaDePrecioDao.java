package com.nantia.repartonantia.listadeprecios;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ListaDePrecioDao {

    @Query("SELECT * FROM listaDePrecio")
    List<ListaDePrecio> getAll();

    @Query("SELECT * FROM listaDePrecio WHERE lista_precios_id IN (:ids)")
    List<ListaDePrecio> loadAllByIds(int[] ids);

    @Query("SELECT * FROM listaDePrecio WHERE nombre_lista LIKE :nombre")
    ListaDePrecio findByName(String nombre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ListaDePrecio... listasDePrecio);

    @Delete
    void delete(ListaDePrecio listaDePrecio);

    @Query("DELETE FROM listadeprecio")
    void nukeTable();

}
