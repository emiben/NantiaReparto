package com.nantia.repartonantia.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FechaHelper {

  private static final String fechaPattern =  "yyyy-MM-dd HH:mm:ss";

  public static String getStringDate(){
    DateFormat dateFormat = new SimpleDateFormat(fechaPattern, Locale.US);
    Date date = new Date();
    String fecha = dateFormat.format(date);
    return fecha.substring(0, fecha.length() - 5);
  }

  public static String getFechParaMostrar(String fecha){
    String fechaFormateada = "";
    String[] parts = fecha.split("T");
    String[] fechaParts = parts[0].split("-");
    fechaFormateada = fechaParts[2]+"/"+fechaParts[1]+"/"+fechaParts[0]+" "+parts[1].substring(0, 5);

    return fechaFormateada;
  }

  public static String getFechParaMostrar(Date fecha){
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    return dateFormat.format(fecha);
  }

  public static String getFechaParaEnviar(Date fecha){
    DateFormat dateFormat2 = new SimpleDateFormat(fechaPattern, Locale.US);
    String fechaString = dateFormat2.format(fecha);
    return fechaString.substring(0, fechaString.length() - 5);
  }

}
