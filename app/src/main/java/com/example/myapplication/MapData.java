package com.example.myapplication;

public class MapData { //Данные о каждом тайле

    public Tile[][] type;
    public Tile[][] floor;
    public int[] playerPos = new int[2]; //0 - x, 1 - y
    public MapData(int w, int h) {
         type = new Tile[w][h];//создает матрицу плиток размеров w*h
         floor = new Tile[40][60];//создает всю карту
    } // я забыл как это работает, но оно работает
}
