package com.nantia.repartonantia.map;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.HashMap;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;
import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteListaMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;
    private MapView mapView;
    private ArrayList<Cliente> clientes;
    private HashMap<Marker, Cliente> clientesMarkers;


    public ClienteListaMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_lista_map, container, false);

        MapsInitializer.initialize(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            clientes = (ArrayList<Cliente>) getArguments().getSerializable(KEY_CLIENTE_LISTA);
        }

        mapView = view.findViewById(R.id.cliente_lista_mapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
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
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
}
