package com.example.exploradordelugaresturisticos.ui.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exploradordelugaresturisticos.databinding.FragmentLugaresBinding;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LugaresViewModel galleryViewModel =
                new ViewModelProvider(this).get(LugaresViewModel.class);

        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}