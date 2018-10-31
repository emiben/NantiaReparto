package com.nantia.repartonantia.venta;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

@Dao
public interface VentaDao {

    @Query("SELECT * FROM venta")
    List<Venta> getAll();

    @Query("SELECT * FROM venta WHERE venta_id IN (:ids)")
    List<Venta> loadAllByIds(int[] ids);

    @Query("SELECT * FROM venta WHERE actualizado = 0")
    List<Venta> getVentaNoActualizado();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Venta... ventas);

    @Delete
    void delete(Venta venta);

    @Query("DELETE FROM venta")
    void nukeTable();
}
