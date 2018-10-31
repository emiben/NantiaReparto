package com.nantia.repartonantia.cliente;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TipoDeDocTypeConverter {

  private static Gson gson = new Gson();

  @TypeConverter
  public static TipoDocumento stringToTipoDocumento(String data) {
    if (data.equals("null")) {
      return null;
    }

    return TipoDocumento.valueOf(data);
  }

  @TypeConverter
  public static String tipoDocumentoToString(TipoDocumento tipoDocumento){
    if(tipoDocumento == null){
      return null;
    }
    return tipoDocumento.name();
  }

}
