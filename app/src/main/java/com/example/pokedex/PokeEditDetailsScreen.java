package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PokeEditDetailsScreen extends AppCompatActivity {

    ImageView iv_pokemonAvatar;
    TextView et_pokemonNumber, et_pokemonName, et_pokemonDescription, et_pokemonHeight,
            et_pokemonWeight, et_pokemonEntryData;
    Button btn_confirm, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_edit_details_screen);

        iv_pokemonAvatar = findViewById(R.id.imageView_pokemonAvatarEditScreen);
        et_pokemonNumber = findViewById(R.id.textView_pokeNumberEditScreen);
        et_pokemonName = findViewById(R.id.textView_pokeNameEditScreen);
        et_pokemonDescription = findViewById(R.id.textView_pokeDescriptionEditScreen);
        et_pokemonHeight = findViewById(R.id.textView_pokeHeightEditScreen);
        et_pokemonWeight = findViewById(R.id.textView_pokeWeightEditScreen);
        et_pokemonEntryData = findViewById(R.id.textView_pokeEntryDataEditScreen);
        btn_confirm = findViewById(R.id.button_confirmEditScreen);
        btn_cancel = findViewById(R.id.button_cancelEditScreen);

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
        et_pokemonNumber.setText(String.format("%03d", receivedPokeDataObject.getPokeNum()));
        et_pokemonName.setText(receivedPokeDataObject.getPokeName());
        et_pokemonDescription.setText(receivedPokeDataObject.getPokeDescription());
        et_pokemonHeight.setText(Float.toString(receivedPokeDataObject.getPokeHeight()));
        et_pokemonWeight.setText(Float.toString(receivedPokeDataObject.getPokeWeight()));
        et_pokemonEntryData.setText(receivedPokeDataObject.getPokeEntryData());

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_details = new Intent(PokeEditDetailsScreen.this, PokeDetailsScreen.class);
                intent_details.putExtra("key_pokeDataObject", receivedPokeDataObject);
                startActivity(intent_details);
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = et_pokemonName.getText().toString();
                String inputDescription = et_pokemonDescription.getText().toString();
                float inputHeight = Float.parseFloat(String.valueOf(et_pokemonHeight.getText()));
                float inputWeight = Float.parseFloat(String.valueOf(et_pokemonWeight.getText()));
                String inputEntryData = et_pokemonEntryData.getText().toString();

                DBHelper database = new DBHelper(getApplication(), "pokedex_database", null, 1);
                database.getWritableDatabase();

                long l = database.updatePokedexEntry(receivedPokeDataObject.getPokeNum(), inputName, inputDescription,
                        inputHeight, inputWeight, inputEntryData);
                if(l>0) {
                    Toast.makeText(PokeEditDetailsScreen.this, "Pokemon updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PokeEditDetailsScreen.this, "Unable to update!", Toast.LENGTH_SHORT).show();
                }
                database.close();
            }
        });
    }
}