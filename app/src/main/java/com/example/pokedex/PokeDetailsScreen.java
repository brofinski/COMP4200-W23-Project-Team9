package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PokeDetailsScreen extends AppCompatActivity {

    ImageView iv_pokemonAvatar;
    TextView tv_pokemonNumber, tv_pokemonName, tv_pokemonDescription, tv_pokemonHeight,
        tv_pokemonWeight, tv_pokemonEntryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details_screen);

        iv_pokemonAvatar = findViewById(R.id.imageView_pokemonAvatar);
        tv_pokemonNumber = findViewById(R.id.textView_pokeNumberDetailsScreen);
        tv_pokemonName = findViewById(R.id.textView_pokeNameDetailsScreen);
        tv_pokemonDescription = findViewById(R.id.textView_pokeDescriptionDetailsScreen);
        tv_pokemonHeight = findViewById(R.id.textView_pokeHeightDetailsScreen);
        tv_pokemonWeight = findViewById(R.id.textView_pokeWeightDetailsScreen);
        tv_pokemonEntryData = findViewById(R.id.textView_pokeEntryDataDetailsScreen);

        Intent intentFromPokeList = getIntent();
        PokeData receivedPokeDataObject = (PokeData) intentFromPokeList.getSerializableExtra("key_pokeDataObject");

        // pokemon avatar goes here
        tv_pokemonNumber.setText(String.format("%03d", receivedPokeDataObject.getPokeNum()));
        tv_pokemonName.setText(receivedPokeDataObject.getPokeName());
        tv_pokemonDescription.setText(receivedPokeDataObject.getPokeDescription());
        tv_pokemonHeight.setText(Float.toString(receivedPokeDataObject.getPokeHeight()));
        tv_pokemonWeight.setText(Float.toString(receivedPokeDataObject.getPokeWeight()));
        tv_pokemonEntryData.setText(receivedPokeDataObject.getPokeEntryData());
    }
}