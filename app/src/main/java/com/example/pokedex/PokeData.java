package com.example.pokedex;

import java.text.DecimalFormat;

public class PokeData {
    private int pokeNum;
    private String pokeName;
    private String pokeDescription;
    private String pokeEntryData;
    private float pokeHeight;
    private float pokeWeight;

    // used to round decimal answers to two places
    // default rounding mode is RoundingMode.HALF_EVEN
    // see more information at link: https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.0");
    // to use, type df.format(numVariable) and it will come out as two decimal places rounded up
    // example: variableName = = Float.parseFloat(df.format(floatNameToConvert));

    public PokeData(int pokeNum, String pokeName) {
        this.pokeNum = pokeNum;
        this.pokeName = pokeName;
    }

    public int getPokeNum() {
        return this.pokeNum;
    }

    public String getPokeName() {
        return this.pokeName;
    }
}
