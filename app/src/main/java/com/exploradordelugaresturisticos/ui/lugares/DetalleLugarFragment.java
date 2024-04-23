package com.exploradordelugaresturisticos.ui.lugares;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exploradordelugaresturisticos.R;

public class DetalleLugarFragment extends Fragment {

    private DetalleLugarViewModel mViewModel;

    public static DetalleLugarFragment newInstance() {
        return new DetalleLugarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_lugar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleLugarViewModel.class);
        // TODO: Use the ViewModel
    }

}