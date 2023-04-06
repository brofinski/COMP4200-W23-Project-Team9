package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PokeDetailsScreen extends AppCompatActivity {

    ImageView iv_pokemonAvatar;
    TextView tv_pokemonNumber, tv_pokemonName, tv_pokemonDescription, tv_pokemonHeight,
        tv_pokemonWeight, tv_pokemonEntryData;
    Button btn_back, btn_edit;

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
        btn_back = findViewById(R.id.button_back);
        btn_edit = findViewById(R.id.button_edit);

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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back = new Intent(PokeDetailsScreen.this, MainActivity.class);
                startActivity(intent_back);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_edit = new Intent(PokeDetailsScreen.this, PokeEditDetailsScreen.class);
                intent_edit.putExtra("key_pokeDataObject", receivedPokeDataObject);
                startActivity(intent_edit);
            }
        });
    }

}