package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RepartoDao {

    @Query("SELECT * FROM reparto")
    List<Reparto> getAll();

    @Query("SELECT * FROM reparto WHERE reparto_id IN (:ids)")
    List<Reparto> loadAllByIds(int[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Reparto... repartos);

    @Delete
    void delete(Reparto reparto);

    @Query("DELETE FROM reparto WHERE reparto_id = :repartoId")
    void deleteById(long repartoId);

    @Query("DELETE FROM reparto")
    void nukeTable();

}
