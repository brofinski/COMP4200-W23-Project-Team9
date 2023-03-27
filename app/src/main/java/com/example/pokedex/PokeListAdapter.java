package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class PokeListAdapter extends RecyclerView.Adapter<PokeListAdapter.MyListViewHolder> {

    private ArrayList<PokeData> pokeDataList;
    private Context context;

    public PokeListAdapter(ArrayList<PokeData> pokeDataList, Context context) {
        this.pokeDataList = pokeDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        PokeData pokeData = pokeDataList.get(position);
        //holder.pokeNumber.setText(String.valueOf(pokeData.getPokeNum()));
        holder.pokeNumber.setText(String.format("%03d", pokeData.getPokeNum())); //formats the num so there is always 3 digits
        holder.pokeName.setText(pokeData.getPokeName());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "You clicked on Pokemon #" + pokeData.getPokeNum();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                Intent intent_toPokeDetailsScreen = new Intent(context, PokeDetailsScreen.class);

                context.startActivity(intent_toPokeDetailsScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokeDataList.size();
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        TextView pokeNumber;
        TextView pokeName;
        ImageView IVpokeball;
        CardView cardView;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeNumber = itemView.findViewById(R.id.tv_pokeNumber);
            pokeName = itemView.findViewById(R.id.tv_pokeName);
            IVpokeball = itemView.findViewById(R.id.iv_pokeball);
            cardView = itemView.findViewById(R.id.card_View);

            Glide.with(itemView).load(R.drawable.pokeball).placeholder(R.mipmap.ic_launcher).into(IVpokeball);
        }
    }
}
