package com.ella.reycicle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterRecyclerView  extends RecyclerView.Adapter<AdapterRecyclerView.viewHolder> {
    private final ArrayList<ItemModel> dataitem;

    private final Context context;

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView textHead;
        TextView textSubhead;
        ImageView imageIcon;
        LinearLayoutCompat parentLayout;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textHead = itemView.findViewById(R.id.text_title);
            textSubhead = itemView.findViewById(R.id.text_subtitle);
            imageIcon = itemView.findViewById(R.id.imagelist);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

    AdapterRecyclerView(Context context,ArrayList<ItemModel> dataitem) {
        this.context = context;
        this.dataitem = dataitem;
    }
    @NonNull
    @Override
    public AdapterRecyclerView.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.viewHolder holder, int position) {

        TextView textHead = holder.textHead;
        TextView textSubhead = holder.textSubhead;
        ImageView imageIcon = holder.imageIcon;

        textHead.setText(dataitem.get(position).getName());
        textSubhead.setText(dataitem.get(position).getType());
        imageIcon.setImageResource(dataitem.get(position).getImage());

        holder.parentLayout.setOnClickListener(v -> {

                if (dataitem.get(position).getName().equals("Hukum Mim dan Nun Bertasydid")) {
                    context.startActivity(new Intent(context, activity2.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Nun Sukun dan Tanwin")) {
                    context.startActivity(new Intent(context, activity3.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Mim Sukun")) {
                     context.startActivity(new Intent(context, activity4.class));
                }
                if (dataitem.get(position).getName().equals("Idghom")) {
                    context.startActivity(new Intent(context, activity5.class));
                }
                if (dataitem.get(position).getName().equals("Ikhfa' Jadid")) {
                    context.startActivity(new Intent(context, activity6.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Lam Ta'rif")) {
                    context.startActivity(new Intent(context, activity7.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Lam Jalalah")) {
                    context.startActivity(new Intent(context, activity8.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Ro'")) {
                    context.startActivity(new Intent(context, activity9.class));
                }
                if (dataitem.get(position).getName().equals("Qolqolah")) {
                    context.startActivity(new Intent(context, activity10.class));
                }
                if (dataitem.get(position).getName().equals("Hukum Mad")) {
                   context.startActivity(new Intent(context, activity11.class));
                }
        });

    }

    @Override
    public int getItemCount() {
        return dataitem.size();
    }


}
