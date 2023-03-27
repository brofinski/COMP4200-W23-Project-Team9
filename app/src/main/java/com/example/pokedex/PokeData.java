package com.example.pokedex;

public class PokeData {
    private int pokeNum;
    private String pokeName;

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
