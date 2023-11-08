package com.cursehantar.completecontrol.cardViewDispositivo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cursehantar.completecontrol.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewVersion;
        TextView txtMarcaCell;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.txtNombreCell);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.txtNumeroCell);
            this.txtMarcaCell = (TextView) itemView.findViewById(R.id.txtMarcaCell);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imgCell);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        //view.setOnClickListener(DisposiActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        TextView txtMarcaCell = holder.txtMarcaCell;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getVersion());
        txtMarcaCell.setText(dataSet.get(listPosition).getMarca());
        imageView.setImageResource(dataSet.get(listPosition).getImage());


        final int finalListPosition = listPosition;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalListPosition >= 0 && finalListPosition < MyData.interactArray.length) {
                    Class<?> targetActivityClass = MyData.interactArray[finalListPosition];

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

