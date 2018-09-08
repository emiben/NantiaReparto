package com.nantia.repartonantia.reparto.Map.Helpers;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Ruta implements Serializable {

    private int distance;
    private int duracion;
    private List<LatLng> camino;

    public Ruta(JSONObject json) {
        try {
            JSONObject overviewPolylines = json.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            camino = PolyUtil.decode(encodedString);
            JSONArray newTempARr = json.getJSONArray("legs");
            JSONObject newDisTimeOb = newTempARr.getJSONObject(0);

            JSONObject distOb = newDisTimeOb.getJSONObject("distance");
            JSONObject timeOb = newDisTimeOb.getJSONObject("duration");
            distance = distOb.getInt("value");
            duracion = distOb.getInt("value");
        }
        catch (JSONException exception){
            exception.printStackTrace();
        }
    }

    public int getDistance() {
        return distance;
    }

    public int getDuracion() {
        return duracion;
    }

    public List<LatLng> getCamino() {
        return camino;
    }
}


