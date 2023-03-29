package com.example.pokedex;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PokeData implements Serializable {
    private int pokeNum;
    private String pokeName;
    private String pokeDescription;
    private String pokeEntryData;
    private int pokeType1, pokeType2;
    private float pokeHeight;
    private float pokeWeight;

    // used to round decimal answers to two places
    // default rounding mode is RoundingMode.HALF_EVEN
    // see more information at link: https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.0");
    // to use, type df.format(numVariable) and it will come out as two decimal places rounded up
    // example: variableName = = Float.parseFloat(df.format(floatNameToConvert));

    public PokeData(int pokeNum, String pokeName, String pokeDescription, String pokeEntryData, int pokeType1, int pokeType2, float pokeHeight, float pokeWeight) {
        this.pokeNum = pokeNum;
        this.pokeName = pokeName;
        this.pokeDescription = pokeDescription;
        this.pokeEntryData = pokeEntryData;
        this.pokeType1 = pokeType1;
        this.pokeType2 = pokeType2;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }

    public int getPokeNum() {
        return this.pokeNum;
    }

    public String getPokeName() {
        return this.pokeName;
    }

    public String getPokeDescription() { return this.pokeDescription; }

    public String getPokeEntryData() { return this.pokeEntryData; }

    public float getPokeHeight() { return this.pokeHeight; }

    public float getPokeWeight() { return this.pokeWeight; }
}
