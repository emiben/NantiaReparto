package com.nantia.repartonantia.stock;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

@Dao
public interface StockDao {

    @Query("SELECT * FROM stock")
    List<Stock> getAll();

    @Query("SELECT * FROM stock WHERE stock_id IN (:ids)")
    List<Stock> loadAllByIds(int[] ids);

    @Query("SELECT * FROM stock WHERE actualizado = 0")
    List<Stock> getStockNoActualizado();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Stock... stocks);

    @Update
    void updateStock(Stock... stocks);

    @Delete
    void delete(Stock stock);

    @Query("DELETE FROM stock")
    void nukeTable();
}
