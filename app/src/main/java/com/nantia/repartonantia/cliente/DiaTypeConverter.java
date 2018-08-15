package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DiaTypeConverter {

  private static Gson gson = new Gson();

  @TypeConverter
  public static List<Dia> stringToDiaList(String data) {
    if (data == null) {
      return Collections.emptyList();
    }

    Type listType = new TypeToken<List<Dia>>() {}.getType();

    return gson.fromJson(data, listType);
  }

  @TypeConverter
  public static String diaListToString(List<Dia> dias){
    return gson.toJson(dias);
  }

}
