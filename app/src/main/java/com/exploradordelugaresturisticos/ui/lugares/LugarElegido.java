package com.exploradordelugaresturisticos.ui.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

public class LugarElegido extends Fragment {
    private LugarElegidoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_lugar_elegido, container, false);

        // Asegurarse de que el ViewModel está configurado correctamente
        viewModel = new ViewModelProvider(this).get(LugarElegidoViewModel.class);

        if (getArguments() != null && getArguments().containsKey("lugar")) {
            LugarTuristico lugar = (LugarTuristico) getArguments().getSerializable("lugar");
            viewModel.setLugarTuristico(lugar);
        }

        // Observar los cambios en el ViewModel y actualizar la UI
        viewModel.getLugarM().observe(getViewLifecycleOwner(), new Observer<LugarTuristico>() {
            @Override
            public void onChanged(LugarTuristico lugar) {
                TextView nombre = view.findViewById(R.id.tvNombreLugar);
                TextView descripcion = view.findViewById(R.id.tvDescripcionLugar);
                TextView horarios = view.findViewById(R.id.tvHorariosLugar);
                TextView ubicacion = view.findViewById(R.id.tvUbicacionLugar);
                ImageView foto = view.findViewById(R.id.ivFotoLugar);

                nombre.setText(lugar.getNombre());
                descripcion.setText(lugar.getDescripcion());
                horarios.setText("Horarios: " + lugar.getHorarios());
                ubicacion.setText("Ubicación: " + lugar.getDireccion());
                foto.setImageResource(lugar.getFoto());
            }
        });

        return view;
    }
}
