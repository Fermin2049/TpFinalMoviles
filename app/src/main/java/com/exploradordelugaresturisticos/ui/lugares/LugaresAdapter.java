package com.exploradordelugaresturisticos.ui.lugares;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exploradordelugaresturisticos.R;
import com.exploradordelugaresturisticos.entidades.LugarTuristico;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolderPepe> {

    private List<LugarTuristico> listaDeLugares;
    private Context context;
    private LayoutInflater li;

    public LugaresAdapter(List<LugarTuristico> listaDeLugares, Context context, LayoutInflater li) {
        this.listaDeLugares = listaDeLugares;
        this.context = context;
        this.li = li;
    }

    public void setLugares(List<LugarTuristico> lugares) {
        listaDeLugares.clear();
        listaDeLugares.addAll(lugares);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_lugar, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        LugarTuristico lugar = listaDeLugares.get(position);

        holder.nombre.setText(lugar.getNombre());
        holder.descripcion.setText(lugar.getDescripcion());
        holder.foto.setImageResource(lugar.getFoto());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LugarElegido.class);
                intent.putExtra("lugar", lugar);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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

}

