package com.exploradordelugaresturisticos.ui.lugares;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    LugaresViewModel LugaresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         LugaresViewModel =new ViewModelProvider(this).get(LugaresViewModel.class);

        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUbicacion;

        LugaresViewModel.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                textView.setText("latitud "+ location.getLatitude() + " longitud"+location.getLongitude());
            }
        });

        LugaresViewModel.ObtenetUltimaUbicacion();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        LugaresViewModel.pararLectura();
    }
}