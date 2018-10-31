package com.nantia.repartonantia.listadeprecios;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ProductoListaTypeConverter {

  private static Gson gson = new Gson();

  @TypeConverter
  public static List<ProductoLista> stringToProductoLista(String data) {
    if (data == null) {
      return Collections.emptyList();
    }

    Type listType = new TypeToken<List<ProductoLista>>() {}.getType();

    return gson.fromJson(data, listType);
  }

  @TypeConverter
  public static String productoListaToString(List<ProductoLista> productosLista){
    return gson.toJson(productosLista);
  }

}
