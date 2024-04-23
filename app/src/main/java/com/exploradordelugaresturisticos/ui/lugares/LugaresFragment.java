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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;

import java.util.ArrayList;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    private LugaresViewModel LugaresViewModel;
    private RecyclerView recyclerView;
    private LugaresAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LugaresViewModel = new ViewModelProvider(this).get(LugaresViewModel.class);
        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerViewLugares; // Asegúrate de que tienes este ID en tu layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LugaresAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        LugaresViewModel.getLugares().observe(getViewLifecycleOwner(), lugares -> {
            // Actualiza el adaptador con la lista de lugares
            adapter.setLugares(lugares);
        });

        return binding.getRoot();
    }
}