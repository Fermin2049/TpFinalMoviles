package com.exploradordelugaresturisticos.ui.mapa;

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
import androidx.lifecycle.ViewModelProvider;

import com.exploradordelugaresturisticos.entidades.LugarTuristico;
import com.exploradordelugaresturisticos.ui.lugares.LugaresViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MapaViewModel extends AndroidViewModel {

    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;
    LocationCallback callback;
    private MutableLiveData<List<LugarTuristico>> mLugaresTuristicos;

    private LugaresViewModel lugaresViewModel;

    public MapaViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
        lugaresViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(LugaresViewModel.class);

    }

    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            this.mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("salida", "Sin permisos");
            return;
        }

        Task<Location> task = fused.getLastLocation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            task.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLocation.postValue(location);
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
                // super.onLocationResult(locationResult);
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLastLocation();
                mLocation.postValue(location);
            }
        };

        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void paraLecturaPermanente(){
        fused.removeLocationUpdates(callback);
    }

    public LiveData<List<LugarTuristico>> getListaLugares() {
        return lugaresViewModel.getListaLugares();
    }
}