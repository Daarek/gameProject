package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

public class Render { //рендеринг карты
    Bitmap bit;//создание биткарты
    ImageView map;//создание карты
    private int width;
    private int height;
    MapData mapData;
    Setup setup;
    Colors colors;

    public Render(Context context, ImageView m, int w, int h) {//получаю всё что надо
        width = w;
        height = h;
        map = m;
    }

    public void setup () {//настройка карты
        bit = Bitmap.createBitmap(width * 21, height * 21, Bitmap.Config.ARGB_8888); //создание пустой битмапы
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();
        colors = setup.getColors();
    }
    public void generate(int x, int y, int[][] image){

        for (int i = x * 21; i < (x + 1) * 21; i++){
            for (int j = y * 21; j < (y + 1) * 21; j++){
                bit.setPixel(i, j, image[i - (x * 21)][j - (y * 21)]);
            }
        }

        finish();
    }
    public void update(){
        for (int i = 0; i <= 20; i++){
            for (int j = 0; j <= 40; j++){
                int[][] color = new int[21][21];
                switch (mapData.type[i][j]){
                    case TREE: color = colors.treeImage; break;
                    case WALL: color = colors.wallImage; break;
                    case GRASS: color = colors.grassImage; break;
                    case BUSH: color = colors.bushImage; break;
                    case EMPTY: color = colors.emptyImage; break;
                    case STONE: color = colors.stoneImage; break;
                }
                generate(i, j, color);
                colors.colorMap[i][j] = color;
            }
        }
    }
    public void finish () {
        BitmapDrawable bitDraw = new BitmapDrawable(null, bit);
        bitDraw.setFilterBitmap(false);//биткарта больше не выглядит как инвалид
        map.setImageDrawable(bitDraw);//установка биткарты
    }

}
