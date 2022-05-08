package com.dinhhuy.onthith;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlainAdapter extends RecyclerView.Adapter<PlainAdapter.PlainViewHolder>{

    private List<Plain> dsPlain;
    private IClickAddToCard iClickAddToCard;

    public interface IClickAddToCard{
        void onClickAddToCard(Button btnAdd, Plain plain);
    }

    public void setData(List<Plain> mPlain, IClickAddToCard iClickAddToCard){
        this.dsPlain = mPlain;
        this.iClickAddToCard = iClickAddToCard;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlainViewHolder holder, int position) {
        Plain plant = dsPlain.get(position);
        if(plant == null) return;

        holder.imgPlain.setImageResource(plant.getResouurceImg());
        holder.name.setText(plant.getName());

        holder.btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickAddToCard.onClickAddToCard(holder.btnAddCard,plant);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(dsPlain !=null) return dsPlain.size();
        return 0;
    }

    public class PlainViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        Button btnAddCard;
        ImageView imgPlain;
        public PlainViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            btnAddCard = itemView.findViewById(R.id.btnAdd);
            imgPlain = itemView.findViewById(R.id.img_Main);
        }
    }
}
