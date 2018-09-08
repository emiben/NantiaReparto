package com.nantia.repartonantia.reparto.Map.Helpers;

import java.util.List;

public interface GetDirectionsListener {
     void rutasObtenidas(List<Ruta> rutas);
     void noHayRutasDisponibles();
}
