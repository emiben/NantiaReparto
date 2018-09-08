package com.nantia.repartonantia.reparto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.reparto.Geolocalizacion.Helpers.ControladorGeolocalizacion;
import com.nantia.repartonantia.reparto.Geolocalizacion.Helpers.GeolocalizacionListener;
import com.nantia.repartonantia.reparto.Map.Helpers.MapRouteHelper;


public class RepartoMapFragment extends Fragment implements OnMapReadyCallback {



    private MapView mapView;
    private GoogleMap googleMap;
    private View view;
    private Marker marker;
    private MarkerOptions markerOptions;
    private ControladorGeolocalizacion controladorGeolocalizacion;



    public RepartoMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reparto_map, container, false);
        MapsInitializer.initialize(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mapView = (MapView)view.findViewById(R.id.repartomapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controladorGeolocalizacion = new ControladorGeolocalizacion(context.getSystemService(Context.LOCATION_SERVICE),context);
        controladorGeolocalizacion.setListener(geolocalizacionListener);
//        if (controladorGeolocalizacion.GPSHabilitado()) {
//            controladorGeolocalizacion.obtenerLocalizacion();
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (controladorGeolocalizacion.GPSHabilitado()) {
            controladorGeolocalizacion.obtenerLocalizacion();
        }

    }

    private final GeolocalizacionListener geolocalizacionListener = new GeolocalizacionListener() {
        @Override
        public void localizacionActualizada(double longitud, double latitud) {
            // hacer algo
            if(googleMap != null){
                LatLng currentLatLng = new LatLng(latitud,
                        longitud);
                MapRouteHelper.crearRuta(currentLatLng, new LatLng(42.316976,-83.185978), googleMap);
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                        15);
                googleMap.moveCamera(update);
            }
        }
    };

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
