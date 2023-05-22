package com.example.miniblog.controleur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniblog.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList Articles_titre,Articles_id,Articles_contenu;
    int position;
    CustomAdapter(Context context,ArrayList Articles_titre,ArrayList Articles_id,ArrayList Articles_contenu){
        this.context = context;
        this.Articles_titre = Articles_titre;
        this.Articles_id = Articles_id;
        this.Articles_contenu = Articles_contenu;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            this.position = position;
            holder.Articles_titres.setText(String.valueOf(Articles_titre.get(position)));
            holder.Articles_contenu.setText(String.valueOf(Articles_contenu.get(position)));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra("article",String.valueOf(Articles_titre.get(position)));
                    intent.putExtra("contenu",String.valueOf(Articles_contenu.get(position)));
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return Articles_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Articles_titres,Articles_contenu,text;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            Articles_titres = itemView.findViewById(R.id.Articles_titre);
            Articles_contenu = itemView.findViewById(R.id.Articles_contenu);
        }
    }
}
