package com.example.exploradordelugaresturisticos.ui.salir;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exploradordelugaresturisticos.R;
import com.example.exploradordelugaresturisticos.databinding.FragmentMapaBinding;
import com.example.exploradordelugaresturisticos.databinding.FragmentSalirBinding;
import com.example.exploradordelugaresturisticos.ui.mapa.MapaViewModel;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapaViewModel homeViewModel =
                new ViewModelProvider(this).get(MapaViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSalir;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}