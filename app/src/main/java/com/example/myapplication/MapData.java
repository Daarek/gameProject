package com.example.myapplication;

public class MapData { //Данные о каждом тайле

    public Tile[][] type;
    public Tile[][] floor;
    public int[] playerPos = new int[2]; //0 - x, 1 - y
    public MapData(int w, int h, int scrw, int scrh) {
         type = new Tile[scrw + 1][scrh + 1];//создает визуальную карту
         floor = new Tile[w][h];//создает всю карту
    } // я забыл как это работает, но оно работает
}
