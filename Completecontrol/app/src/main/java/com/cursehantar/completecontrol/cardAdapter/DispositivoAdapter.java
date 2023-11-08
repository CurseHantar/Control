package com.cursehantar.completecontrol.cardAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.R;


import java.util.ArrayList;

public class DispositivoAdapter extends RecyclerView.Adapter<DispositivoAdapter.MyViewHolder> {
    private final ArrayList<Dispositivo> dataSet;
    private Context context;
    private LayoutInflater inflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombreCell,numeroCell,marcaCell;
        ImageView imagenCell;

        public MyViewHolder(View itemView){
            super(itemView);
            this.nombreCell = (TextView) itemView.findViewById(R.id.nombreCell);
            this.numeroCell = (TextView) itemView.findViewById(R.id.numeroCell);
            this.marcaCell = (TextView) itemView.findViewById(R.id.marcaCell);
            this.imagenCell = (ImageView) itemView.findViewById(R.id.imagenCell);
        }
    }

    public DispositivoAdapter(ArrayList<Dispositivo> data) {
        this.context = context;
        this.dataSet = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_activity, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView nombreCell = holder.nombreCell;
        TextView numeroCell = holder.numeroCell;
        TextView marcaCell = holder.marcaCell;
        ImageView imgCvInicio = holder.imagenCell;

        nombreCell.setText(dataSet.get(listPosition).getNombreCell());
        numeroCell.setText(dataSet.get(listPosition).getNumeroCell());
        marcaCell.setText(dataSet.get(listPosition).getMarcaCell());
        imgCvInicio.setImageResource(dataSet.get(listPosition).getImagen());

        final int finalListPosition = listPosition;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalListPosition >= 0 && finalListPosition < MisDatos.interactInArray.length){
                    Class<?> targetActivityClass = MisDatos.interactInArray[finalListPosition];

                    Intent i = new Intent(v.getContext(), targetActivityClass);
                    v.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
