package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class Render extends View {

    Bitmap bit;//создание биткарты
    ImageView map;//создание карты

    int width;
    int height;

    public Render(Context context, int w, int h) {//надо, не трогай
        super(context);
        width = w;
        height = h;
    }

    public void setup () {//настройка карты

        bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //создание пустой битмапы
        map = findViewById(R.id.map);

    }
    public void generate(int x, int y, int color /* координаты пикселя и цвет*/){//первая прорисовка карты
        bit.setPixel(x, y, color);
    }
    public void finish (){
        map.setImageBitmap(bit);//установка биткарты поверх карты
    }

}
