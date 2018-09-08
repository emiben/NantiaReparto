package com.nantia.repartonantia.map.helpers;

import java.util.List;

public interface GetDirectionsListener {
     void rutasObtenidas(List<Ruta> rutas);
     void noHayRutasDisponibles();
}
