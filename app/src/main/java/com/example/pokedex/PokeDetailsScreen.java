package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PokeDetailsScreen extends AppCompatActivity {

    ImageView iv_pokemonAvatar;
    TextView tv_pokemonNumber, tv_pokemonName, tv_pokemonType, tv_pokemonHeight,
        tv_pokemonWeight, tv_pokemonDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details_screen);

        iv_pokemonAvatar = findViewById(R.id.imageView_pokemonAvatar);
        tv_pokemonNumber = findViewById(R.id.textView_pokeNumberDetailsScreen);
        tv_pokemonName = findViewById(R.id.textView_pokeNameDetailsScreen);
        tv_pokemonType = findViewById(R.id.textView_pokeTypeDetailsScreen);
        tv_pokemonHeight = findViewById(R.id.textView_pokeHeightDetailsScreen);
        tv_pokemonWeight = findViewById(R.id.textView_pokeWeightDetailsScreen);
        tv_pokemonDescription = findViewById(R.id.textView_pokeDescriptionDetailsScreen);
    }
}