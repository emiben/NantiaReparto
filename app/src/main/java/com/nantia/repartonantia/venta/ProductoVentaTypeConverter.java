package com.nantia.repartonantia.venta;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ProductoVentaTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<ProductoVenta> stringToProductoVentaLista(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProductoVenta>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String productoVentaListToString(List<ProductoVenta> productosVenta){
        return gson.toJson(productosVenta);
    }
}
