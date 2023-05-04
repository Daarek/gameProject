package com.example.myapplication;

public class MapData { //Данные о каждом тайле

    public Tile[][] type;
    public MapData(int w, int h) {
        type = new Tile[w][h];
    }
}
