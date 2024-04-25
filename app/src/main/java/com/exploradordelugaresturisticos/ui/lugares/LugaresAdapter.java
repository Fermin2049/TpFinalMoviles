package com.exploradordelugaresturisticos.ui.lugares;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolderPepe> {

    private List<LugarTuristico> listaDeLugares;
    private OnLugarClickListener clickListener;

    public LugaresAdapter(List<LugarTuristico> listaDeLugares, OnLugarClickListener clickListener) {
        this.listaDeLugares = listaDeLugares;
        this.clickListener = clickListener;
    }

    public void setLugares(List<LugarTuristico> lugares) {
        listaDeLugares.clear();
        listaDeLugares.addAll(lugares);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.item_lugar, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        final LugarTuristico lugar = listaDeLugares.get(position);

        holder.nombre.setText(lugar.getNombre());
        holder.descripcion.setText(lugar.getDescripcion());
        holder.foto.setImageResource(lugar.getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lugarSeleccionado", lugar);
                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_LugarElegidoFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDeLugares.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView nombre, descripcion;
        ImageView foto;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvTitulo);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            foto = itemView.findViewById(R.id.ivFoto);
        }
    }

    public interface OnLugarClickListener {
        void onLugarClicked(LugarTuristico lugar);
    }


}
