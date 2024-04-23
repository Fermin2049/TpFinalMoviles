package com.exploradordelugaresturisticos.ui.lugares;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class LugaresViewModel extends AndroidViewModel {

    private MutableLiveData<Location> mLocation = new MutableLiveData<>(); // Asegurarse de que siempre está inicializado
    private FusedLocationProviderClient fused;
    LocationCallback callback;

    public LugaresViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(application);
    }

    public LiveData<Location> getMLocation() {
        return mLocation;
    }

    public void lecturaPermanente() {
        LocationRequest request = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    mLocation.postValue(locationResult.getLastLocation()); // Postea la ubicación actual a LiveData
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Manejar la solicitud de permisos
            return; // No hacer nada si no hay permisos
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void pararLectura() {
        if (callback != null) {
            fused.removeLocationUpdates(callback);
            callback = null;  // Limpiar el callback
        }
    }


}