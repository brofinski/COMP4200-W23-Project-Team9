package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the views and buttons
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //testing out the card view list
        /**
        ArrayList<PokeData> pokeData = new ArrayList<>();
        int testNum = 69;
        int testNum2 = 70;
        pokeData.add(new PokeData(testNum, "Chris"));
        pokeData.add(new PokeData(testNum2, "Baljot"));
        pokeData.add(new PokeData(71, "Alex"));
         **/

        //creating the db or loading it
        DBHelper database = new DBHelper(getApplication(), "pokedex_database", null, 1);
        database.getReadableDatabase();

        ArrayList<PokeData> pokeData = new ArrayList<>();
        Cursor cursor = database.displayData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data to display!", Toast.LENGTH_SHORT).show();
        }
        else {
            /**int pokeNum, pokeType1, pokeType2;
            String pokeName, pokeDescription, pokeEntryData;
            float pokeHeight, pokeWeight; **/
            while(cursor.moveToNext()) {
                //creating each PokeData object
                // follows the form: int number, String name, String description,
                // String entry_data, int type1, int type2, float height, floatweight
                pokeData.add(new PokeData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getInt(4), cursor.getInt(5),
                        cursor.getFloat(6), cursor.getFloat(7)));
            }
        }

        /**
        pokeData.add(new PokeData(1, "Bulbasaur", "Seed Pokemon",
                "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokémon.",
                8,12,(float)0.7,(float)6.9));
        pokeData.add(new PokeData(2, "Ivysaur", "Seed Pokémon",
                "When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.",
                8,12,(float)1,(float)13));
        pokeData.add(new PokeData(3, "Venusaur", "Seed Pokémon",
                "The plant blooms when it is absorbing solar energy. It stays on the move to seek sunlight.",
                8,12,(float)2,(float)100));
         **/

        PokeListAdapter pokeListAdapter = new PokeListAdapter(pokeData, MainActivity.this);
        recyclerView.setAdapter(pokeListAdapter);

    }

    public void QuitApplication(View view) {
        //MainActivity.this.finish();
        finishAffinity();
        //super.finish();
        //System.exit(0);
    }
}