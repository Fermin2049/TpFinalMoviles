package com.exploradordelugaresturisticos.ui.configuracion;


import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.*;

public class ConfiguracionViewModel extends ViewModel {

    private Resources resources;


    public void setResources(Resources resources) {
        this.resources = resources;
    }


    private MutableLiveData<String> tipoMapa = new MutableLiveData<>();

    public LiveData<String> getTipoMapa() {
        return tipoMapa;
    }

    public void setTipoMapa(String tipo) {
        tipoMapa.setValue(tipo);
    }

    public int obtenerTipoMapa() {
        String selectedMapType = tipoMapa.getValue();

        if (selectedMapType == null) {
            return GoogleMap.MAP_TYPE_NORMAL;
        }

        switch (selectedMapType) {
            case "Satelital":
            case "Satellite":
                return GoogleMap.MAP_TYPE_SATELLITE;
            case "Terreno":
            case "Terrain":
                return GoogleMap.MAP_TYPE_TERRAIN;
            case "Híbrido":
            case "Hybrid":
                return GoogleMap.MAP_TYPE_HYBRID;
            default:
                return GoogleMap.MAP_TYPE_NORMAL;
        }
    }




}