package com.exploradordelugaresturisticos.ui.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.ArrayList;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    private LugaresViewModel lugaresViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        lugaresViewModel = new ViewModelProvider(requireActivity()).get(LugaresViewModel.class);
        binding = FragmentLugaresBinding.inflate(inflater, container, false);

        // Configurar RecyclerView
        binding.recyclerViewLugares.setLayoutManager(new LinearLayoutManager(getContext()));
        // Obtén el NavController
        NavController navController = NavHostFragment.findNavController(this);
        LugaresAdapter adapter = new LugaresAdapter(new ArrayList<>(), new LugaresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LugarTuristico lugar) {
                // Manejo del clic en un elemento del RecyclerView
                // Crea un Bundle y coloca el LugarTuristico serializable
                Bundle bundle = new Bundle();
                bundle.putSerializable("lugar", lugar);
                // Usa el ID de la acción para navegar
                navController.navigate(R.id.action_lugaresFragment_to_detalleLugarFragment, bundle);
            }
        });
        binding.recyclerViewLugares.setAdapter(adapter);

        // Observar cambios en los datos
        lugaresViewModel.getLugares().observe(getViewLifecycleOwner(), lugares -> {
            // Actualizar el adaptador con los datos más recientes
            adapter.setLugares(lugares);
        });

        return binding.getRoot();
    }
}