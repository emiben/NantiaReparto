package com.nantia.repartonantia.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaHelper {


  public static String getStringDate(){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    Date date = new Date();
    return dateFormat.format(date);
  }

  public static String getFechParaMostrar(String fecha){
    String fechaFormateada = "";
    String[] parts = fecha.split("T");
    String[] fechaParts = parts[0].split("-");
    fechaFormateada = fechaParts[2]+"/"+fechaParts[1]+"/"+fechaParts[0]+" "+parts[1].substring(0, 5);

    return fechaFormateada;
  }

}
