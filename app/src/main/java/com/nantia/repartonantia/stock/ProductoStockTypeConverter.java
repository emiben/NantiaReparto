package com.nantia.repartonantia.stock;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ProductoStockTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<ProductoStock> stringToProductoStockLista(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductoStock>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String productoStockListToString(List<ProductoStock> productosStock){
        return gson.toJson(productosStock);
    }
}
