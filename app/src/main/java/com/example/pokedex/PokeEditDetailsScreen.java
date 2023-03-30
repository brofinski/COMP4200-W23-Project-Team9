package com.example.pokedex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PokeEditDetailsScreen extends AppCompatActivity {

    private ImageView iv_pokemonAvatar;
    private TextView et_pokemonNumber, et_pokemonName, et_pokemonDescription, et_pokemonHeight,
            et_pokemonWeight, et_pokemonEntryData;
    private Button btn_confirm, btn_back;
    private PokeData receivedPokeDataObject;

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
        btn_back = findViewById(R.id.button_backEditScreen);

        Intent intentFromPokeList = getIntent();
        receivedPokeDataObject = (PokeData) intentFromPokeList.getSerializableExtra("key_pokeDataObject");

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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_details = new Intent(PokeEditDetailsScreen.this, PokeDetailsScreen.class);
                intent_details.putExtra("key_pokeDataObject", receivedPokeDataObject);
                startActivity(intent_details);
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
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

                // updating the Pokemon edit screen
                PokeData newPokeDataObject = null;
                Cursor cursor = database.searchPokedexByPokemonNumber(receivedPokeDataObject.getPokeNum());
                if (cursor.getCount()==0) {
                    Toast.makeText(PokeEditDetailsScreen.this, "Could not find the Pokemon!", Toast.LENGTH_SHORT).show();
                } else {
                    /**int pokeNum, pokeType1, pokeType2;
                     String pokeName, pokeDescription, pokeEntryData;
                     float pokeHeight, pokeWeight; **/
                    while(cursor.moveToNext()) {
                        //creating the new PokeData object
                        // follows the form: int number, String name, String description,
                        // String entry_data, int type1, int type2, float height, floatweight
                        newPokeDataObject = new PokeData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                cursor.getString(3), cursor.getInt(4), cursor.getInt(5),
                                cursor.getFloat(6), cursor.getFloat(7));
                    }
                }
                database.close();
                et_pokemonNumber.setText(String.format("%03d", newPokeDataObject.getPokeNum()));
                et_pokemonName.setText(newPokeDataObject.getPokeName());
                et_pokemonDescription.setText(newPokeDataObject.getPokeDescription());
                et_pokemonHeight.setText(Float.toString(newPokeDataObject.getPokeHeight()));
                et_pokemonWeight.setText(Float.toString(newPokeDataObject.getPokeWeight()));
                et_pokemonEntryData.setText(newPokeDataObject.getPokeEntryData());
                updateTheReceivedPokeDataObject(newPokeDataObject);
            }
        });
    }
    /**
    private void populateEditDetailsScreenWithPokeDataObjectInformation() {
        String inputName = et_pokemonName.getText().toString();
        String inputDescription = et_pokemonDescription.getText().toString();
        float inputHeight = Float.parseFloat(String.valueOf(et_pokemonHeight.getText()));
        float inputWeight = Float.parseFloat(String.valueOf(et_pokemonWeight.getText()));
        String inputEntryData = et_pokemonEntryData.getText().toString();
    }
     **/
    private void updateTheReceivedPokeDataObject(PokeData newPokeDataObject) {
        this.receivedPokeDataObject = newPokeDataObject;
    }
}