package com.example.myapplication;

public enum Tile { //типы тайлов

    EMPTY("Empty"),
    TREE("Tree"),
    STONE("Stone"),
    BUSH("Bush"),
    GRASS("Grass"),
    PLAYER("Player");

    private String display;
    Tile (String display){ //Я фиг его знает как это работает
        this.display = display;
    }
    public String toString(){
        return display;
    }
}