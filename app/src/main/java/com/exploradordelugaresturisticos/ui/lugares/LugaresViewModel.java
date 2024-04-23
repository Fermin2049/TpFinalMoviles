package com.exploradordelugaresturisticos.ui.lugares;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LugaresViewModel extends AndroidViewModel {

    private MutableLiveData<List<LugarTuristico>> lugares;

    public LugaresViewModel(@NonNull Application application) {
        super(application);

        // Crear datos de ejemplo con las coordenadas que proporcionaste
        List<LugarTuristico> listaDeLugares = new ArrayList<>();
        listaDeLugares.add(new LugarTuristico(
                new LatLng(-33.291958448935176, -66.31237669763469),
                "Parque de las Naciones",
                "Un hermoso parque lleno de áreas verdes y espacios recreativos.",
                Arrays.asList(R.drawable.parque_naciones1, R.drawable.parque_naciones2, R.drawable.parque_naciones3)
        ));
        listaDeLugares.add(new LugarTuristico(
                new LatLng(-33.28491899502414, -66.29938450784749),
                "Plaza del Cerro",
                "Una plaza histórica en el corazón de la ciudad.",
                Arrays.asList(R.drawable.plaza_cerro1, R.drawable.plaza_cerro2, R.drawable.plaza_cerro3)
        ));
        listaDeLugares.add(new LugarTuristico(
                new LatLng(-33.276218344724825, -66.33764389799929),
                "Villa Deportiva",
                "Complejo deportivo para la práctica de diferentes disciplinas.",
                Arrays.asList(R.drawable.villa_deportiva1, R.drawable.villa_deportiva2, R.drawable.villa_deportiva3)
        ));
        listaDeLugares.add(new LugarTuristico(
                new LatLng(-33.30508500684787, -66.32341823345463),
                "San Luis Shopping",
                "Centro comercial con una amplia variedad de tiendas y entretenimiento.",
                Arrays.asList(R.drawable.san_luis_shopping1, R.drawable.san_luis_shopping2, R.drawable.san_luis_shopping3)
        ));
        listaDeLugares.add(new LugarTuristico(
                new LatLng(-33.25882860782914, -66.24646435794719),
                "Monumento al Pueblo Puntano",
                "Monumento en honor a la historia y cultura de la región.",
                Arrays.asList(R.drawable.monumento_pueblo1, R.drawable.monumento_pueblo2, R.drawable.monumento_pueblo3)
        ));

        // Agregar la lista de lugares al LiveData
        lugares = new MutableLiveData<>(listaDeLugares);
    }

    public LiveData<List<LugarTuristico>> getLugares() {
        if (lugares == null) {
            lugares = new MutableLiveData<>();
            // Carga los lugares aquí...
        }
        return lugares;
    }


}