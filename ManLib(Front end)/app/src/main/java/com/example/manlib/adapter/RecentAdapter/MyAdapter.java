package com.example.manlib.adapter.RecentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.manlib.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> modelArrayList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> modelArrayList){
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recemment_ajoute_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = modelArrayList.get(position);
        holder.Auteur.setText(model.getName());
        holder.Titre.setText("");
        holder.Description.setText("model.getDescription()");
        holder.Prix.setText(model.getRate());
        holder.Quantite.setText("01");
//        Glide.with(holder.Photo.getContext()).load(model.getPhoto()).into(holder.Photo);
//        Glide.with(holder.Photo.getContext()).using(new FirebaseImageLoader()).load(model.getPhoto()).into(holder.Photo);

//                holder.Photo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AppCompatActivity activity = (AppCompatActivity)v.getContext();
//                        activity.getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.test, new BookDetailFragment(model.getAuteur(),model.getTitre(),model.getDescription(),model.getPhoto())).commit();
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Auteur, Description, Titre, Prix, Quantite;
        ImageView Photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Auteur = (TextView) itemView.findViewById(R.id.auteurR);
            Description = (TextView) itemView.findViewById(R.id.descriptionR);
            Photo = (ImageView) itemView.findViewById(R.id.photo_livreR);
            Titre = (TextView) itemView.findViewById(R.id.titreR);
            Prix = (TextView)itemView.findViewById(R.id.prixR);
            Quantite = (TextView) itemView.findViewById(R.id.qteR);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION && listener != null){
//
//                    }
//                }
//            });
        }
    }

//    public  interface OnitemOnClickListener {
//        void onItemClick (DocumentSnapshot documentSnapshot, int position);
//    }
//
//    public void setOnitemOnClickListener(OnitemOnClickListener listener){
//        this.listener = (View.OnClickListener) listener;
//    }

}
