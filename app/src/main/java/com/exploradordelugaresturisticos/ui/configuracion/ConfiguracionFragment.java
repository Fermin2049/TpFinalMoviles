package com.exploradordelugaresturisticos.ui.configuracion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.databinding.FragmentConfiguracionBinding;

public class ConfiguracionFragment extends Fragment {

    private ConfiguracionViewModel viewModel;
    private Spinner tipoMapaSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ConfiguracionViewModel.class);
        viewModel.setResources(getResources());
        tipoMapaSpinner = view.findViewById(R.id.map_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.tipo_mapas,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoMapaSpinner.setAdapter(adapter);

        tipoMapaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoMapaSeleccionado = parent.getItemAtPosition(position).toString();
                viewModel.setTipoMapa(tipoMapaSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


}