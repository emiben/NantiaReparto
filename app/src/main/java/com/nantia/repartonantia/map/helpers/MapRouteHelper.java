package com.nantia.repartonantia.map.helpers;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Collections;
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

    public static void traerRutaMasCorta(LatLng origen, final List<LatLng> destinos, final RutaHelperListener listener) {
        final ArrayList<Ruta> rutasADestino = new ArrayList<Ruta>();
        for (int i = 0; i < destinos.size();i++){
            GetDirectionsAsync direccion = new GetDirectionsAsync();
            direccion.setListener(new GetDirectionsListener() {
                @Override
                public void rutasObtenidas(List<Ruta> rutas) {
                    Collections.sort(rutas);
                    rutasADestino.add(rutas.get(0));
                    if (rutasADestino.size() == destinos.size()) {
                        Collections.sort(rutasADestino);
                        listener.rutaMasCortaADestinoEncontrada(rutasADestino.get(0));
                    }
                }

                @Override
                public void noHayRutasDisponibles() {
                    // decidir como manejar error
                }
            });
            direccion.execute(origen, destinos.get(i));
        }
    }

    public static Polyline trazarRuta(Ruta ruta, GoogleMap map){
        PolylineOptions options = new PolylineOptions().width(10).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < ruta.getCamino().size(); i++) {
            LatLng point = ruta.getCamino().get(i);
            options.add(point);
        }
        return map.addPolyline(options);
    }

    public static interface RutaHelperListener {
        void rutaMasCortaADestinoEncontrada(Ruta ruta);
    }
}
