package com.exploradordelugaresturisticos;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;
    LocationCallback callback;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }
}
