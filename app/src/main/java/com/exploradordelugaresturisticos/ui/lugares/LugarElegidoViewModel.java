package com.exploradordelugaresturisticos.ui.lugares;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.exploradordelugaresturisticos.entidades.LugarTuristico;

public class LugarElegidoViewModel extends AndroidViewModel {

    private MutableLiveData<LugarTuristico> lugarTuristicoM;

    public LugarElegidoViewModel(@NonNull Application application) {
        super(application);
        lugarTuristicoM = new MutableLiveData<>();
    }

    public LiveData<LugarTuristico> getLugarM() {
        return lugarTuristicoM;
    }

    // Método para establecer el lugar turístico directamente
    public void setLugarTuristico(LugarTuristico lugar) {
        lugarTuristicoM.setValue(lugar);
    }
}