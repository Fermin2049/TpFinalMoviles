package com.exploradordelugaresturisticos.ui.lugares;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.databinding.ItemLugarBinding;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugarViewHolder> {

    private List<LugarTuristico> lugares;
    private OnItemClickListener listener;
    private NavController navController;

    public interface OnItemClickListener {
        void onItemClick(LugarTuristico lugar);
    }

    // Pasar el listener al constructor
    public LugaresAdapter(List<LugarTuristico> lugares, OnItemClickListener listener) {
        this.lugares = lugares;
        this.listener = listener;
    }

    // Método para actualizar la lista de lugares
    public void setLugares(List<LugarTuristico> nuevosLugares) {
        this.lugares = nuevosLugares;
        notifyDataSetChanged();  // Notifica a RecyclerView que los datos han cambiado
    }

    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLugarBinding binding = ItemLugarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LugarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarViewHolder holder, int position) {
        LugarTuristico lugar = lugares.get(position);
        holder.bind(lugar, listener, navController);
    }

    @Override
    public int getItemCount() {
        return lugares != null ? lugares.size() : 0;
    }

    static class LugarViewHolder extends RecyclerView.ViewHolder {
        ItemLugarBinding binding;


        LugarViewHolder(ItemLugarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(final LugarTuristico lugar, final OnItemClickListener listener, NavController navController) {
            // ... configurar las vistas ...
            itemView.setOnClickListener(v -> {
                // Usar el NavController que pasaste
                Bundle bundle = new Bundle();
                bundle.putSerializable("lugar", lugar);
                navController.navigate(R.id.action_lugaresFragment_to_detalleLugarFragment, bundle);
            });
        }
    }


}

