package com.example.myapplication;

public class MapData { //Данные о каждом тайле

    public Tile[][] type;
    public Tile[][] floor;
    public int[][] amount;
    public int[] playerPos = new int[2]; //0 - x, 1 - y на глобальной карте
    public int[] topLeftCorner = new int[2];
    public int[] bottomRightCorner = new int[2];
    public MapData(int w, int h, int scrw, int scrh) {
        type = new Tile[scrw][scrh];//создает визуальную карту
        floor = new Tile[w][h];//создает всю карту
        amount = new int[w][h];//карта ресурсов
        playerPos[0] = w/2;
        playerPos[1] = h/2;
        topLeftCorner[0] = 10; //Всё фиксированно, ибо и так работает
        topLeftCorner[1] = 10;
        bottomRightCorner[0] = 30;
        bottomRightCorner[1] = 50;
    } // я забыл как это работает, но оно работает

}
