package com.nantia.repartonantia.map.helpers;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class GetDirectionsAsync extends AsyncTask<LatLng, Void, List<Ruta>> {

    JSONParser jsonParser;
    String DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private GetDirectionsListener listener;
    private LatLng origen;
    private LatLng destino;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Ruta> doInBackground(LatLng... params) {
        LatLng start = params[0];
        LatLng end = params[1];
        this.origen = start;
        this.destino = end;
        HashMap<String, String> points = new HashMap<>();
        points.put("origin", start.latitude + "," + start.longitude);
        points.put("destination", end.latitude + "," + end.longitude);
//        points.put("key", "AIzaSyAFgLgcG3YA6vjIcxY296t3JNP1UU1z7wY");
        points.put("sensor", "true");
        points.put("mode", "driving");

        jsonParser = new JSONParser();

        JSONObject obj = jsonParser.makeHttpRequest(DIRECTIONS_URL, "GET", points, true);

        if (obj == null) return null;

        try {
            List<Ruta> list = null;

            JSONArray routeArray = obj.getJSONArray("routes");
//            JSONObject routes = routeArray.getJSONObject(0);
//            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
//            String encodedString = overviewPolylines.getString("points");
//            list = PolyUtil.decode(encodedString);
            for (int i = 0; i< routeArray.length(); i++){
                JSONObject rutaJson = routeArray.getJSONObject(i);
                Ruta ruta = new Ruta(rutaJson);
                ruta.setOrigen(this.origen);
                ruta.setDestino(this.destino);
                if (list == null){
                    list = new ArrayList<Ruta>();
                }
                list.add(ruta);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Ruta> rutas) {
        if (rutas == null) {
            listener.noHayRutasDisponibles();
        }
        else {
            listener.rutasObtenidas(rutas);
        }
//        if (pointsList == null) return;
//

    }

    public void setListener(GetDirectionsListener listener) {
        this.listener = listener;
    }
}
