package com.nantia.repartonantia.map.helpers;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface GetDirectionsListener {
     void rutasObtenidas(List<Ruta> rutas);
     void noHayRutasDisponibles();
}
