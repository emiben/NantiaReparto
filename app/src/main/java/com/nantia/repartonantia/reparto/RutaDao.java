package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

@Dao
public interface RutaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Ruta... rutas);

    @Delete
    void delete(Ruta ruta);

}
