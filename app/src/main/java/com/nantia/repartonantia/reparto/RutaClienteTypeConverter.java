package com.nantia.repartonantia.reparto;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class RutaClienteTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<RutaCliente> stringToRutaClienteLista(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<RutaCliente>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String rutaClienteListToString(List<RutaCliente> rutasCliente){
        return gson.toJson(rutasCliente);
    }
}
