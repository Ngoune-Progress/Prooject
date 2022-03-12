package com.example.manlib.adapter.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manlib.R;
import com.google.android.gms.common.Feature;

import java.util.ArrayList;

public class VedetteAdapter extends RecyclerView.Adapter<VedetteAdapter.VedetteviewHolder> {
    ArrayList<VedetteClass> locationVedette;

    public VedetteAdapter(ArrayList<VedetteClass> locationVedette){
        this.locationVedette = locationVedette;
    }

    @NonNull
    @Override
    public VedetteviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vedette_card_design, parent, false);
        VedetteviewHolder vedetteviewHolder = new VedetteviewHolder(view);

        return vedetteviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VedetteviewHolder holder, int position) {

        VedetteClass vedetteClass = locationVedette.get(position);
        holder.image.setImageResource(vedetteClass.getImage());
        holder.titre.setText(vedetteClass.getTitre());
        holder.desription.setText(vedetteClass.getDescription());
        holder.rate.setRating(vedetteClass.getRate());

    }

    @Override
    public int getItemCount() {
        return locationVedette.size();
    }

    public static class VedetteviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView titre, desription;
        RatingBar rate;

        public VedetteviewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.photo_livre);
            titre = itemView.findViewById(R.id.titre);
            desription = itemView.findViewById(R.id.description);
            rate = itemView.findViewById(R.id.rating);

        }
    }
}
