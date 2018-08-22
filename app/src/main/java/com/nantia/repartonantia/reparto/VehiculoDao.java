package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

public interface VehiculoDao {

    @Query("SELECT * FROM vehiculo")
    List<Vehiculo> getAll();

    @Query("SELECT * FROM vehiculo WHERE vehiculo_id IN (:ids)")
    List<Vehiculo> loadAllByIds(int[] ids);

    @Query("SELECT * FROM vehiculo WHERE actualizado = 0")
    List<Vehiculo> getVehiculoNoActualizado();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Vehiculo... vehiculos);

    @Delete
    void delete(Vehiculo vehiculo);
}
