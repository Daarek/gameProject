package com.example.myapplication;

public class Character { //мэин хиро
    public int x; // x y на поле
    public int y; //!!!переделать координаты под биткарту!!!
    public int layoutX; // x y на экране
    public int layoutY;
    public Character (int x, int y) {
        this.x = x;
        this.y = y;
        this.layoutX = x * 10;
        this.layoutY = y * 10;
    }

    public void setX(int x){ //чтобы всегда знал где находится
        this.x = x;
        layoutX = x * 10;
    }
    public void setY(int y){
        this.y = y;
        layoutY = y * 10;
    }
}
