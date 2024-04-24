package com.exploradordelugaresturisticos.ui.lugares;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.ArrayList;
import java.util.List;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    private LugaresAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LugaresViewModel viewModel =
                new ViewModelProvider(this).get(LugaresViewModel.class);

        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.listaLugares;


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new LugaresAdapter(new ArrayList<>(), getContext(), getLayoutInflater());
        recyclerView.setAdapter(adapter);

        viewModel.getListaLugares().observe(getViewLifecycleOwner(), new Observer<List<LugarTuristico>>() {
            @Override
            public void onChanged(List<LugarTuristico> lugares) {
                adapter.setLugares(lugares);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}