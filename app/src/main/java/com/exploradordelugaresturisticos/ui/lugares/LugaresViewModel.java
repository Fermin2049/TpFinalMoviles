package com.exploradordelugaresturisticos.ui.lugares;


import android.app.Application;
import android.content.Intent;

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

    private MutableLiveData<List<LugarTuristico>> listaLugares;

    public LugaresViewModel(@NonNull Application application) {
        super(application);
        listaLugares = new MutableLiveData<>();
        inicializarLista();
    }

    public LiveData<List<LugarTuristico>> getListaLugares() {
        return listaLugares;
    }

    private void inicializarLista() {
        List<LugarTuristico> lugares = new ArrayList<>();

        lugares.add(new LugarTuristico("Parque de las Naciones",
                -33.291958448935176,
                -66.31237669763469,
                "Un Parque en el centro de la ciudad",
                "06:00 a 21:00",
                "Av Eva Peron",
                R.drawable.parque_naciones1));

        lugares.add(new LugarTuristico("Plaza del Cerro",
                -33.291958448935176,
                -66.31237669763469,
                "Un Parque lleno de vida",
                "06:00 a 21:00",
                "Villa de praga",
                R.drawable.plaza_cerro1));

        lugares.add(new LugarTuristico("Villa Deportiva",
                -33.291958448935176,
                -66.31237669763469,
                "Villa Recreativa Para Realizar Actividades",
                "06:00 a 21:00",
                "Av Justo Daract",
                R.drawable.villa_deportiva3));

        lugares.add(new LugarTuristico("San Luis Shopping",
                -33.291958448935176,
                -66.31237669763469,
                "Centro comercial",
                "06:00 a 21:00",
                "25 de Mayo",
                R.drawable.san_luis_shopping1));

        lugares.add(new LugarTuristico("Monumento al Pueblo Puntano",
                -33.291958448935176,
                -66.31237669763469,
                "Monumento al Pueblo Puntano",
                "06:00 a 21:00",
                "25 de Mayo",
                R.drawable.monumento_pueblo1));

        listaLugares.setValue(lugares);
    }


}