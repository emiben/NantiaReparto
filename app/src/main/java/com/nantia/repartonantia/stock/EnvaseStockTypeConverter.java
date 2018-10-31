package com.nantia.repartonantia.stock;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class EnvaseStockTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<EnvaseStock> stringToEnvaseStockLista(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<EnvaseStock>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String envaseStockListToString(List<EnvaseStock> envasesStock){
        return gson.toJson(envasesStock);
    }
}
