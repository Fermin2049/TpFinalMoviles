package com.exploradordelugaresturisticos.ui.vistaMapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.ui.lugares.LugaresViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class vistaMapaFragment extends Fragment {
    private LugaresViewModel lugaresViewModel;
    private GoogleMap googleMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap gMap) {
            Log.d("vistaMapaFragment", "Mapa listo.");
            googleMap = gMap; // Inicializa la variable de clase.
            // No coloques marcadores aquí a menos que sean fijos.
            // La ubicación actual se manejará en actualizarUbicacionEnMapa().
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vista_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        // Asegúrate de que LugaresViewModel ya esté inicializado aquí
        lugaresViewModel = new ViewModelProvider(this).get(LugaresViewModel.class);

        // Observa los cambios en la ubicación desde LugaresViewModel
        lugaresViewModel.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                // Este log ayudará a saber cuándo se recibe la ubicación
                Log.d("vistaMapaFragment", "Ubicación actualizada: " + location);
                actualizarUbicacionEnMapa(location);
            }
        });
    }


    // Método para actualizar la ubicación en el mapa.
    private void actualizarUbicacionEnMapa(Location location) {
        Log.d("vistaMapaFragment", "Actualizando ubicación en el mapa: " + location);
        if (googleMap != null && location != null) {
            LatLng ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.clear(); // Limpia marcadores anteriores.
            googleMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Estoy aquí"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 15)); // Ajusta el zoom según sea necesario.
        }
    }



}