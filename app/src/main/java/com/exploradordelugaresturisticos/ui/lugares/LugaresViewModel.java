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

    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;

    LocationCallback callback;

    public LugaresViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            this.mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public void ObtenetUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permiso no concedido: Log para depurar
            Log.d("LugaresViewModel", "Permisos de ubicación no concedidos.");
            return;
        }
        Task<Location> task = fused.getLastLocation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            task.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Log para mostrar la ubicación recuperada o si es null
                    Log.d("LugaresViewModel", "Ubicación recuperada: " + location);
                    if (location != null) {
                        mLocation.postValue(location);
                    } else {
                        // Si la ubicación es null, también debes loguearlo
                        Log.d("LugaresViewModel", "Ubicación recuperada es null");
                    }
                }
            });
        }
    }


    public void lecturaPermanente() {
        LocationRequest request = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult != null) {
                    return;
                }
                Location location = locationResult.getLastLocation();
                mLocation.postValue(location);
                Log.d("LugaresViewModel", "Iniciando lectura permanente de ubicación...");
            }
        };
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void pararLectura() {
        if (callback != null) {
            fused.removeLocationUpdates(callback);
            callback = null;  // Setear a null después de remover para evitar usos futuros
        }

    }


}