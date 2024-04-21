package com.example.exploradordelugaresturisticos.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SalirViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Salir");
    }

    public LiveData<String> getText() {
        return mText;
    }
}