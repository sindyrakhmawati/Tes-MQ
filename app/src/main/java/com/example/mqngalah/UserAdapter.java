package com.ella.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ella.notification.Room.Users;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<Users> usersList;

    private AdapterListener adapterListener;

    public UserAdapter(Context context,AdapterListener Listener){
        this.context = context;
        usersList = new ArrayList<>();
        this.adapterListener=Listener;
    }
    public void addUser(Users users){
        usersList.add(users);
        notifyDataSetChanged();
    }
    public void removeUser(int position){
        usersList.remove(position);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        Users users = usersList.get(position);
        holder.nama.setText(users.getNama());
        holder.jenjang.setText(users.getJenjang());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterListener.OnDelete(users.getId(), adapterPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, jenjang;
        private ImageView delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            jenjang = itemView.findViewById(R.id.jenjang);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
