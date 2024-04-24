package com.exploradordelugaresturisticos.ui.configuracion;

import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exploradordelugaresturisticos.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class ConfiguracionViewModel extends ViewModel {

    private Resources resources;
    private String codigoIdiomaSeleccionado = "espanol";

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void cambiarIdioma(String idiomaElegido) {
        codigoIdiomaSeleccionado = obtenerCodigoIdioma(idiomaElegido);
        establecerIdioma(codigoIdiomaSeleccionado);
    }

    public String getCodigoIdiomaSeleccionado() {
        return codigoIdiomaSeleccionado;
    }

    private String obtenerCodigoIdioma(String idioma) {
        if (idioma.equals("English")) {
            return "en";
        } else if (idioma.equals("Español")) {
            return "es";
        } else {
            return "es";
        }
    }

    private void establecerIdioma(String codigoIdioma) {
        Locale locale = new Locale(codigoIdioma);
        locale.setDefault(locale);
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
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