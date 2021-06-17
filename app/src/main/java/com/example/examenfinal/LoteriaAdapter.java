package com.example.examenfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoteriaAdapter extends RecyclerView.Adapter<LoteriaAdapter.ViewHolder> {
    private static List<Loteria> mData;
    private static LayoutInflater mInflater;

    public  LoteriaAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<Loteria>();
    }

    // Almacena y recicla las vistas a medida que se desplazan fuera de la pantalla
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TextView textView2;
        TextView textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.numero);
            textView2 = itemView.findViewById(R.id.serie);
            textView3 = itemView.findViewById(R.id.premio);
        }

        @Override
        public void onClick(View view) {

        }
    }


    // Infla el diseño de la fila xml cuando sea necesario
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_api, parent, false);
        return new ViewHolder(view);
    }


    // Une los datos TextView e ImageView en cada fila
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf("\n" + "Numero: " + mData.get(position).numero));
        holder.textView2.setText(String.valueOf("Serie: " + mData.get(position).serie));
        holder.textView3.setText(String.valueOf("Premio: " + mData.get(position).premio + "\n"));
    }


    // Numero de RssItem
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addData(List<Loteria> pruebas) {
        mData.addAll(pruebas);
        notifyDataSetChanged();
    }


    // Devuelve el RssItem del array
    public Loteria getPrueba(int id) {
        return mData.get(id);
    }
}
