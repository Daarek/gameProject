package com.example.myapplication;

public class MapData { //Данные о каждом тайле

    public Tile[][] type;
    public int[] playerPos = new int[2]; //0 - x, 1 - y
    public MapData(int w, int h) {
        type = new Tile[w][h];
    } // я забыл как это работает, но оно работает
}
