package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        ArrayList<PokeData> pokeData = new ArrayList<>();
        int testNum = 69;
        int testNum2 = 70;
        pokeData.add(new PokeData(testNum, "Chris"));
        pokeData.add(new PokeData(testNum2, "Baljot"));
        pokeData.add(new PokeData(71, "Alex"));


        PokeListAdapter pokeListAdapter = new PokeListAdapter(pokeData, MainActivity.this);
        recyclerView.setAdapter(pokeListAdapter);

    }
}