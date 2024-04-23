package com.exploradordelugaresturisticos.ui.vistaMapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.exploradordelugaresturisticos.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class vistaMapaFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Marker currentMarker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Configura la solicitud de ubicación
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        // Define el callback de la ubicación
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (map != null && location != null) {
                        LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        if (currentMarker != null) {
                            currentMarker.remove();
                        }
                        currentMarker = map.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                    }
                }
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inicializa la vista del mapa...
        return inflater.inflate(R.layout.fragment_vista_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Lista de algunos lugares con sus latitudes y longitudes
        LatLng lugar1 = new LatLng(-33.291958448935176, -66.31237669763469);
        LatLng lugar2 = new LatLng(-33.28491899502414, -66.29938450784749);
        LatLng lugar3 = new LatLng(-33.276218344724825, -66.33764389799929);
        LatLng lugar4 = new LatLng(-33.30508500684787, -66.32341823345463);
        LatLng lugar5 = new LatLng(-33.25882860782914, -66.24646435794719);   // Ejemplo: Moscú, Rusia

        // Agregar marcadores en el mapa para cada lugar
        map.addMarker(new MarkerOptions().position(lugar1).title("Parque de las Naciones"));
        map.addMarker(new MarkerOptions().position(lugar2).title("Plaza del Cerro"));
        map.addMarker(new MarkerOptions().position(lugar3).title("Villa Deportiva"));
        map.addMarker(new MarkerOptions().position(lugar4).title("San Luis Shopping"));
        map.addMarker(new MarkerOptions().position(lugar5).title("Monumento al Pueblo Puntano"));

        // Además, puedes mover la cámara a una ubicación específica si lo deseas
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar1, 10));

        // Aquí también puedes iniciar las actualizaciones de ubicación si lo necesitas
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling ActivityCompat#requestPermissions here.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map != null) {
            startLocationUpdates();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }



}