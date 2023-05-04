package com.example.myapplication;

import android.graphics.Color;
import android.widget.ImageView;

public class Setup { //Настройка карты (класс нужен чтобы все не развалилось)

    public int width;
    public int height;
    ImageView map;
    public Setup (int w, int h, ImageView m){
        width = w;
        height = h;
        map = m;
    }

    public void build(){
        Generator generator = new Generator();
        MapData mapData = new MapData(width, height);
        Render render = new Render(MainActivity.Context(), map, width, height); //НеВеРоЯтНо! ОнО рАбОтАет!
        render.setup();

        for (int x = width - 1; x >= 0; x-- ){
            for (int y = height - 1; y >= 0; y--){
                Tile type = generator.generate();
                int color = 0;
                mapData.type[x][y] = type;
                switch (type){
                    case EMPTY: color = Color.rgb(100, 100, 100); break;
                    case TREE: color = Color.rgb(150, 255, 150); break;
                    case STONE: color = Color.rgb(200, 200, 200); break;
                    case GRASS: color = Color.rgb(0, 200, 0); break;
                    case BUSH: color = Color.rgb(80, 180, 0); break;
                }
                render.generate(x , y, color);
            }
        }
        render.finish();

    }

}
