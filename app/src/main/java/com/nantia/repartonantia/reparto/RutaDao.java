package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RutaDao {

    @Query("SELECT * FROM ruta")
    List<Ruta> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Ruta... rutas);

    @Delete
    void delete(Ruta ruta);

    @Query("DELETE FROM ruta")
    void nukeTable();

}
