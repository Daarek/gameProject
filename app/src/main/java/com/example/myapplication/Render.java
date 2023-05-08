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

    public Render(Context context, ImageView m, int w, int h) {//надо, не трогай
        width = w;
        height = h;
        map = m;
    }

    public void setup () {//настройка карты

        bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //создание пустой битмапы
    }
    public void generate(int x, int y, int color /* координаты пикселя и цвет*/){//первая прорисовка карты
        bit.setPixel(x, y, color);
    }
    public void finish (){
        BitmapDrawable bitDraw = new BitmapDrawable(MainActivity.context(), bit);
        bitDraw.setFilterBitmap(false);
        map.setImageDrawable(bitDraw);//установка биткарты поверх карты
    }

}
