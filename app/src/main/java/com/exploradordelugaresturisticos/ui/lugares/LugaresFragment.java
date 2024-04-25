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
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    private LugaresAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Obteniendo el ViewModel
        LugaresViewModel viewModel = new ViewModelProvider(this).get(LugaresViewModel.class);

        // Inflando el layout para este fragment
        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configurando el RecyclerView
        RecyclerView recyclerView = binding.listaLugares;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurando el Adapter
        adapter = new LugaresAdapter(new ArrayList<>(), lugar -> {
            // Creando un Bundle para pasar al LugarElegidoFragment
            Bundle bundle = new Bundle();
            bundle.putSerializable("lugarSeleccionado", lugar);
            Navigation.findNavController(root).navigate(R.id.action_FirstFragment_to_LugarElegidoFragment, bundle);
        });
        recyclerView.setAdapter(adapter);

        // Observando cambios en la lista de lugares
        viewModel.getListaLugares().observe(getViewLifecycleOwner(), lugares -> {
            adapter.setLugares(lugares);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}