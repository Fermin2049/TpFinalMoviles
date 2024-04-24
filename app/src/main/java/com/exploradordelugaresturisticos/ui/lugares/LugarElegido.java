package com.exploradordelugaresturisticos.ui.lugares;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;


public class LugarElegido extends AppCompatActivity {
    private LugarElegidoViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_elegido);

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LugarElegidoViewModel.class);

        mv.getLugarM().observe(this, new Observer<LugarTuristico>() {
            @Override
            public void onChanged(LugarTuristico lugar) {
                TextView nombre = findViewById(R.id.tvNombreLugar);
                TextView descripcion = findViewById(R.id.tvDescripcionLugar);
                TextView horarios = findViewById(R.id.tvHorariosLugar);
                TextView ubicacion  = findViewById(R.id.tvUbicacionLugar);
                ImageView foto = findViewById(R.id.ivFotoLugar);

                nombre.setText(lugar.getNombre());
                descripcion.setText(lugar.getDescripcion());
                horarios.setText("Horarios: " + lugar.getHorarios());
                ubicacion.setText("Ubicación: " + lugar.getDireccion());
                foto.setImageResource(lugar.getFoto());
            }
        });

        mv.recuperarLugar(getIntent());

        Button boton = findViewById(R.id.btVolver);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
