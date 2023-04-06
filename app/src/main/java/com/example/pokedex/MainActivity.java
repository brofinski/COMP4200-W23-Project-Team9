package com.example.pokedex;

import androidx.appcompat.app.AlertDialog;
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

        //creating the db or loading it
        DBHelper database = new DBHelper(getApplication(), "pokedex_database", null, 1);
        database.getReadableDatabase();

        ArrayList<PokeData> pokeData = new ArrayList<>();
        Cursor cursor = database.displayData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data to display!", Toast.LENGTH_SHORT).show();
        }
        else {

            while(cursor.moveToNext()) {
                //creating each PokeData object
                // follows the form: int number, String name, String description,
                // String entry_data, int type1, int type2, float height, floatweight
                pokeData.add(new PokeData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getInt(4), cursor.getInt(5),
                        cursor.getFloat(6), cursor.getFloat(7)));
            }
        }
        database.close();


        PokeListAdapter pokeListAdapter = new PokeListAdapter(pokeData, MainActivity.this);
        recyclerView.setAdapter(pokeListAdapter);

    }

    public void QuitApplication(View view) {
        //MainActivity.this.finish();
        finishAffinity();
        //super.finish();
        //System.exit(0);
    }

    public void AboutApplicationButton(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("About application");
        String aboutAppInfo = "Pokedex Application\n\nCOMP4200\nWinter 2023\nFor: Prof. Shaon Bhatta Shuvo\n\nDeveloped by:\nBaljot Hansi & Christopher Rafinski";
        alertDialog.setMessage(aboutAppInfo);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}