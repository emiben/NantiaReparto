package com.nantia.repartonantia.map;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.geolocalizacion.Helpers.ControladorGeolocalizacion;
import com.nantia.repartonantia.geolocalizacion.Helpers.GeolocalizacionListener;
import com.nantia.repartonantia.map.helpers.MapRouteHelper;
import com.nantia.repartonantia.map.helpers.Ruta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;
import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteListaMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;
    private Boolean mapReady = false;
    private MapView mapView;
    private List<Cliente> clientes;
    private HashMap<Marker, Cliente> clientesMarkers;
    private ControladorGeolocalizacion controladorGeolocalizacion;
    private Boolean reparto;
    private LatLng puntoPartida;


    public ClienteListaMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controladorGeolocalizacion = new ControladorGeolocalizacion(context.getSystemService(Context.LOCATION_SERVICE),context);
        controladorGeolocalizacion.setListener(geolocalizacionListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_lista_map, container, false);

        if(getArguments() != null && getArguments().getBoolean(KEY_REPARTO, false)){
            reparto = true;
        }else {
            clientes = DataHolder.getClientes();
            reparto = false;
        }
        MapsInitializer.initialize(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.cliente_lista_mapView);
        if(mapView != null) {
            mapView.onCreate(null);

            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(reparto){
            clientes = DataHolder.getReparto().getRuta().getClientesSinVisitar();
            if(mapReady){
                googleMap.clear();
                loadData();
            }
        }

    }

    private void loadData(){
        if(clientes != null){
            if(clientesMarkers == null){
                clientesMarkers = new HashMap<>();
            }
            for (Cliente cliente : clientes){
                if(!cliente.getDireccion().getCoordLat().isEmpty() &&
                        !cliente.getDireccion().getCoordLon().isEmpty()){
                    LatLng point = new LatLng(Double.valueOf(cliente.getDireccion().getCoordLat()),
                            Double.valueOf(cliente.getDireccion().getCoordLon()));

                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title(cliente.getNombreCompleto())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                    clientesMarkers.put(marker, cliente);
                }
            }
            controladorGeolocalizacion.obtenerLocalizacion();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //googleMap.setMyLocationEnabled(true);
        if(controladorGeolocalizacion.GPSHabilitado()) {
            controladorGeolocalizacion.obtenerLocalizacion();
        }
        //Setea el mapa en Sta Lucia
        CameraPosition fabrica = CameraPosition.builder()
                .target(new LatLng(-34.455691, -56.387059))
                .zoom(15)
                .bearing(0)
                .tilt(0)
                .build();

        this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(fabrica));
        this.googleMap.setOnInfoWindowClickListener(this);
//        googleMap.setOnMapClickListener(this);
        mapReady = true;
        loadData();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        navigateToCliente(clientesMarkers.get(marker));
    }

    private void navigateToCliente(Cliente cliente){
        Intent i = new Intent(getActivity(), ClienteActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE, cliente);
        i.putExtra(KEY_CLIENTE, b);
        startActivity(i);
    }

    public void setPuntoPartida(LatLng puntoPartida){
        this.puntoPartida = puntoPartida;
    }

    private final GeolocalizacionListener geolocalizacionListener = new GeolocalizacionListener() {
        @Override
        public void localizacionActualizada(double longitud, double latitud) {
            // hacer algo
            setPuntoPartida(new LatLng(latitud,longitud));
            if(googleMap != null){
                LatLng currentLatLng = new LatLng(latitud,
                        longitud);
                MapRouteHelper.crearRuta(currentLatLng, new LatLng(40.762810,-73.944066), googleMap);
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                        15);
                googleMap.moveCamera(update);
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                }
                procesarRutaMasCorta();
            }

        }
    };

    private List<LatLng> parsearDestinos() {
        ArrayList<LatLng> destinosParseados = new ArrayList<LatLng>();
        for (Cliente cliente : clientes) {
            if(cliente.getDireccion().getCoordLat() != null && !cliente.getDireccion().getCoordLat().isEmpty()
                    && cliente.getDireccion().getCoordLon() != null && !cliente.getDireccion().getCoordLon().isEmpty()) {
                LatLng point = new LatLng(Double.valueOf(cliente.getDireccion().getCoordLat()),
                        Double.valueOf(cliente.getDireccion().getCoordLon()));
                destinosParseados.add(point);
            }
        }
        return destinosParseados;
    }

    private void procesarRutaMasCorta(){
        MapRouteHelper.traerRutaMasCorta(puntoPartida, parsearDestinos(), new MapRouteHelper.RutaHelperListener() {
            @Override
            public void rutaMasCortaADestinoEncontrada(Ruta ruta) {
                //trazar ruta en mapa
                MapRouteHelper.trazarRuta(ruta,googleMap);
            }
        });
    }
}

