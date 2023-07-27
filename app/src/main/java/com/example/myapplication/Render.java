package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

public class Render { //рендеринг карты
    Bitmap bit;//создание биткарты
    ImageView map;//создание карты
    int width;
    int height;
    MapData mapData;
    Setup setup;
    Colors colors;

    public Render(Context context, ImageView m, int w, int h) {//получаю всё что надо
        width = w;
        height = h;
        map = m;
    }

    public void setup () {//настройка карты
        bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //создание пустой битмапы
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();
        colors = setup.getColors();
    }
    public void generate(int x, int y, int color ){
        bit.setPixel(x, y, color);
    }
    public void update(){
        for (int i = 0; i <= 20; i++){
            for(int j = 0; j <= 40; j++){
                int color = 0;
                switch (mapData.type[i][j]){
                    case TREE: color = colors.tree; break;
                    case GRASS: color = colors.grass; break;
                    case BUSH: color = colors.bush; break;
                    case EMPTY: color = colors.empty; break;
                    case STONE: color = colors.stone; break;
                }
                generate(i , j, color);
                colors.colorMap[i][j] = color;
            }
        }
    }
    public void finish (){
        BitmapDrawable bitDraw = new BitmapDrawable(MainActivity.context(), bit);
        bitDraw.setFilterBitmap(false);//биткарта больше не выглядит как инвалид
        map.setImageDrawable(bitDraw);//установка биткарты
    }

}/* координаты пикселя и цвет*/
//задаёт один пиксель визуальной карты
