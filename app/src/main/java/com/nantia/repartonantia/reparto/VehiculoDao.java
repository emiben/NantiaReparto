package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface VehiculoDao {

    @Query("SELECT * FROM vehiculo")
    List<Vehiculo> getAll();

    @Query("SELECT * FROM vehiculo WHERE vehiculo_id IN (:ids)")
    List<Vehiculo> loadAllByIds(int[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Vehiculo... vehiculos);

    @Delete
    void delete(Vehiculo vehiculo);

    @Query("DELETE FROM vehiculo")
    void nukeTable();
}
