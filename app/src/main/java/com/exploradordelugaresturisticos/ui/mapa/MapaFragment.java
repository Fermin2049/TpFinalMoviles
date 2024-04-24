package com.exploradordelugaresturisticos.ui.mapa;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.databinding.FragmentMapaBinding;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;
import com.exploradordelugaresturisticos.ui.configuracion.ConfiguracionViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapaBinding binding;
    private MapaViewModel viewModel;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapaBinding.inflate(inflater, container, false);
        //view model de Maps
        viewModel = new ViewModelProvider(this).get(MapaViewModel.class);
        viewModel.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                if (googleMap != null) {
                    LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi Ubicación"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 15));
                }
            }
        });
        viewModel.obtenerUltimaUbicacion();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Necesitamos el vm de config para manejar los tipos de mapa y marcadores
        ConfiguracionViewModel configuracionViewModel = new ViewModelProvider(requireActivity()).get(ConfiguracionViewModel.class);



        configuracionViewModel.getTipoMapa().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String tipoMapa) {
                Log.d("MapsFragment", "Tipo de mapa cambiado a: " + tipoMapa);
                if (googleMap != null) {
                    googleMap.setMapType(configuracionViewModel.obtenerTipoMapa());
                }
            }
        });


    }
}