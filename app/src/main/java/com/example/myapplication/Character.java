package com.example.myapplication;

import static com.example.myapplication.Tile.PLAYER;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

public class Character extends View { //мэин хиро
    public int x; // x и y
    public int y;
    public MapData mapData;
    public double shift;
    public int centerX;
    public int centerY;
    public Character (Context context, int X, int Y) {
        super(context);
        x = X;
        y = Y;
        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        centerX = width/2;
        centerY = height/2;
        shift = (double) width/height;

    }
    public void setReferences(){
        Setup setup = MainActivity.getSetup();
        mapData = setup.getMapData();
    }
    public void create(){
        mapData.type[x][y] = PLAYER;
        mapData.playerPos[0] = x;
        mapData.playerPos[1] = y;
    }
    public void move(double actualX, double eventY){
        double actualY = eventY*shift; //понадобится позже (наверное)
    if (actualX/actualY > 0){ //Верх или право
        if (actualX+actualY > width){ //право
            
        } else {//Верх
            
        }
    } else { //низ или лево
        if (actualX+actualY > width){//низ
            
        } else { //лево
            
        }
    }

}
