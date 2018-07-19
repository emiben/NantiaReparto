package com.nantia.repartonantia.map;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.ClienteNuevoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClienteMapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap googleMap;
    private MapView mapView;
    private View view;
    private Marker marker;
    private MarkerOptions markerOptions;
    private FloatingActionButton fab;

    public ClienteMapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cliente_mapa, container, false);
        MapsInitializer.initialize(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            markerOptions = new MarkerOptions()
                    .position((LatLng) getArguments().getParcelable("latLng"))
                    .title(getString(R.string.cliente_nuevo))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        }

        fab = view.findViewById(R.id.cliente_nuevo_coord_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToClienteNuevo();
            }
        });
        mapView = view.findViewById(R.id.cliente_mapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition fabrica = CameraPosition.builder()
                .target(new LatLng(-34.455691, -56.387059))
                .zoom(15)
                .bearing(0)
                .tilt(0)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(fabrica));
        googleMap.setOnMapClickListener(this);

        if(markerOptions != null){
            marker = googleMap.addMarker(markerOptions);
        }

    }

    @Override
    public void onMapClick(LatLng point) {
        if(marker != null){
            marker.remove();
        }
        marker = googleMap.addMarker(new MarkerOptions()
                .position(point)
                .title(getString(R.string.cliente_nuevo))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
    }

    private void navigateToClienteNuevo(){
        ClienteNuevoFragment clienteNuevoFragment =
                (ClienteNuevoFragment) getActivity().getSupportFragmentManager().findFragmentByTag("clienteNuevoFragment");
        if(marker != null){
            Bundle b = new Bundle();
            b.putDouble("lat", marker.getPosition().latitude);
            b.putDouble("lng", marker.getPosition().longitude);
            clienteNuevoFragment.setArguments(b);
        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteNuevoFragment)
                .commit();
    }
}
