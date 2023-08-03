package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;

public class Colors {

    public Bitmap emptyBitmap;
    public Bitmap treeBitmap;
    public Bitmap stoneBitmap;
    public Bitmap bushBitmap;
    public Bitmap grassBitmap;
    public Bitmap playerBitmap;
    public Bitmap wallBitmap;
    public Setup setup;
    public int[][] emptyImage = new int[21][21];
    public int[][] treeImage = new int[21][21];
    public int[][] stoneImage = new int[21][21];
    public int[][] bushImage = new int[21][21];
    public int[][] grassImage = new int[21][21];
    public int[][] playerImage = new int[21][21];
    public int[][] wallImage = new int[21][21];
    public int[][][][] colorMap;
    public Colors (int width, int height){
        setup = MainActivity.getSetup();
        colorMap = new int[width][height][21][21]; //поверхность земли без игрока
    }
    public void handleBitmaps () {
        emptyBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.empty);
        treeBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.tree);
        stoneBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.stone);
        bushBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.bush);
        grassBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.grass);
        playerBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.player);
        wallBitmap = BitmapFactory.decodeResource(MainActivity.Context().getResources(), R.drawable.wall);

        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                emptyImage[i][j] = emptyBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                treeImage[i][j] = treeBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                stoneImage[i][j] = stoneBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                bushImage[i][j] = bushBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                grassImage[i][j] = grassBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                playerImage[i][j] = playerBitmap.getPixel(i, j);
            }
        }
        for (int i = 0; i < 21; i++){
            for (int j = 0; j < 21; j++){
                wallImage[i][j] = wallBitmap.getPixel(i, j);
            }
        }

    }
}

