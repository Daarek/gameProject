package com.example.myapplication;

import android.graphics.Color;

public class Colors {
    public final int empty = Color.rgb(200, 200, 200);
    public final int tree = Color.rgb(150, 255, 150);
    public final int stone = Color.rgb(100, 100, 100);
    public final int bush = Color.rgb(80, 180, 0);
    public final int grass = Color.rgb(0, 200, 0);
    public final int player = Color.rgb(0, 0, 255);
    public int[][] colorMap;
    public Colors (int width, int height){
        colorMap = new int[width][height];
    }
}
