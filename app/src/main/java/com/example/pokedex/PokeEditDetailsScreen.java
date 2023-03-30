package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PokeEditDetailsScreen extends AppCompatActivity {

    ImageView iv_pokemonAvatar;
    TextView tv_pokemonNumber, tv_pokemonName, tv_pokemonDescription, tv_pokemonHeight,
            tv_pokemonWeight, tv_pokemonEntryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_edit_details_screen);

        iv_pokemonAvatar = findViewById(R.id.imageView_pokemonAvatarEditScreen);
        tv_pokemonNumber = findViewById(R.id.textView_pokeNumberEditScreen);
        tv_pokemonName = findViewById(R.id.textView_pokeNameEditScreen);
        tv_pokemonDescription = findViewById(R.id.textView_pokeDescriptionEditScreen);
        tv_pokemonHeight = findViewById(R.id.textView_pokeHeightEditScreen);
        tv_pokemonWeight = findViewById(R.id.textView_pokeWeightEditScreen);
        tv_pokemonEntryData = findViewById(R.id.textView_pokeEntryDataEditScreen);

        Intent intentFromPokeList = getIntent();
        PokeData receivedPokeDataObject = (PokeData) intentFromPokeList.getSerializableExtra("key_pokeDataObject");

        // setting the poke image dynamically from drawable
        // https://stackoverflow.com/questions/11737607/how-to-set-the-image-from-drawable-dynamically-in-android
        try {
            String uri = "@drawable/p" + receivedPokeDataObject.getPokeNum();
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource, null);
            Glide.with(this).load(res).placeholder(R.mipmap.ic_launcher).into(iv_pokemonAvatar);
        }
        catch (Exception e) {
            // loads a simple placeholder if the poke image could not be found
            Glide.with(this).load(R.mipmap.ic_launcher).into(iv_pokemonAvatar);
        }
        tv_pokemonNumber.setText(String.format("%03d", receivedPokeDataObject.getPokeNum()));
        tv_pokemonName.setText(receivedPokeDataObject.getPokeName());
        tv_pokemonDescription.setText(receivedPokeDataObject.getPokeDescription());
        tv_pokemonHeight.setText(Float.toString(receivedPokeDataObject.getPokeHeight()));
        tv_pokemonWeight.setText(Float.toString(receivedPokeDataObject.getPokeWeight()));
        tv_pokemonEntryData.setText(receivedPokeDataObject.getPokeEntryData());
    }
}