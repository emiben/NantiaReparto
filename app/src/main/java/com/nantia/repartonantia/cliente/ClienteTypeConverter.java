package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ClienteTypeConverter {

  private static Gson gson = new Gson();

  @TypeConverter
  public static List<Cliente> stringToClienteList(String data) {
    if (data == null) {
      return Collections.emptyList();
    }

    Type listType = new TypeToken<List<Cliente>>() {}.getType();

    return gson.fromJson(data, listType);
  }

  @TypeConverter
  public static String clienteListToString(List<Cliente> clientes){
    return gson.toJson(clientes);
  }

}
