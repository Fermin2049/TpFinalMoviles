package com.exploradordelugaresturisticos.ui.lugares;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugarViewHolder> {

    private List<LugarTuristico> lugares; // Tu modelo de datos

    public LugaresAdapter(List<LugarTuristico> lugares) {
        this.lugares = lugares;
    }

    // Método para actualizar la lista de lugares
    public void setLugares(List<LugarTuristico> nuevosLugares) {
        this.lugares = nuevosLugares;
        notifyDataSetChanged();  // Notifica a RecyclerView que los datos han cambiado
    }

    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar, parent, false);
        return new LugarViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LugarViewHolder holder, int position) {
        LugarTuristico lugar = lugares.get(position);
        holder.textViewNombreLugar.setText(lugar.getNombre());
        holder.textViewDescripcionLugar.setText(lugar.getDescripcion());
        // Aquí podrías también cargar las imágenes si tienes una lista de ellas en LugarTuristico.
        // Por ejemplo, podrías mostrar la primera imagen como una imagen de portada.
        // Suponiendo que tienes una forma de obtener el drawable ID del recurso de la imagen.
        if (!lugar.getFotos().isEmpty()) {
            holder.imageViewLugar.setImageResource(lugar.getFotos().get(0));
        }
    }

    @Override
    public int getItemCount() {
        return lugares != null ? lugares.size() : 0;
    }

    static class LugarViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreLugar;
        TextView textViewDescripcionLugar;
        ImageView imageViewLugar;

        public LugarViewHolder(View itemView) {
            super(itemView);
            textViewNombreLugar = itemView.findViewById(R.id.textViewNombreLugar);
            textViewDescripcionLugar = itemView.findViewById(R.id.textViewDescripcionLugar);
            imageViewLugar = itemView.findViewById(R.id.imageViewLugar);
        }
    }

}

