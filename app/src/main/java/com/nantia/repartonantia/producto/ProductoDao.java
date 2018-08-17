package com.nantia.repartonantia.producto;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface ProductoDao {

  @Query("SELECT * FROM producto")
  List<Producto> getAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(Producto... productos);

  @Delete
  void delete(Producto producto);

}
