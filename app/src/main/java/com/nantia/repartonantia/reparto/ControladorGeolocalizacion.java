package com.nantia.repartonantia.reparto;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class ControladorGeolocalizacion {

    private LocationManager locationManager;
    private Object systemService;
    private double longitud;
    private double latitud;
    private GeolocalizacionListener listener;
    private Context context;



    public ControladorGeolocalizacion(Object systemService, Context context) {
        this.systemService = systemService;
        locationManager = (LocationManager) systemService;
        this.context = context;
    }

    public void setListener(GeolocalizacionListener listener) {
        this.listener = listener;
    }

    public Boolean GPSHabilitado() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void obtenerLocalizacion() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(provider, 2 * 20 * 1000, 10, locationListenerBest);
        }
    }

    public void detenerActualizacionesGPS() {
        locationManager.removeUpdates(locationListenerBest);
    }

    private final LocationListener locationListenerBest = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitud = location.getLongitude();
            latitud = location.getLatitude();
            listener.localizacionActualizada(longitud,latitud);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
