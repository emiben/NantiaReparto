package com.nantia.repartonantia.reparto.Map.Helpers;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapRouteHelper {

    public static void crearRuta(LatLng origen,LatLng destino, final GoogleMap map) {
        GetDirectionsAsync direcciones = new GetDirectionsAsync();
        direcciones.setListener(new GetDirectionsListener() {
            @Override
            public void rutasObtenidas(List<Ruta> rutas) {
                Ruta ruta1 = rutas.get(0);
                System.out.println(ruta1.getDuracion());
                System.out.println(ruta1.getDistance());
                System.out.println(ruta1.getCamino());
                trazarRuta(ruta1,map);
            }

            @Override
            public void noHayRutasDisponibles() {
                //error
            }
        });

        direcciones.execute(origen,destino);
    }

    private static Polyline trazarRuta(Ruta ruta, GoogleMap map){
        PolylineOptions options = new PolylineOptions().width(5).color(Color.MAGENTA).geodesic(true);
        for (int i = 0; i < ruta.getCamino().size(); i++) {
            LatLng point = ruta.getCamino().get(i);
            options.add(point);
        }
        return map.addPolyline(options);
    }
}
