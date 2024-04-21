package com.example.exploradordelugaresturisticos.ui.configuracion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfiguracionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is configuracion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}