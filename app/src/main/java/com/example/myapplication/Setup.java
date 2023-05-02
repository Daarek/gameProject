package com.example.myapplication;

import android.graphics.Color;

public class Setup {

    public int width;
    public int height;
    public Setup (int w, int h){
        width = w;
        height = h;
    }

    public void build(){
        Generator generator = new Generator();
        MapData mapData = new MapData();
        Render render = new Render(MainActivity.Context(), width, height); //НеВеРоЯтНо! ОнО рАбОтАет!
        render.setup();

        for (int x = width; x >= 0; x-- ){
            for (int y = height; y >= 0; y--){
                int type = generator.generate();
                mapData.type[x][y] = type;
                int color = 0;
                if(type == 0){color = Color.rgb(100, 100, 100);}
                else if (type == 1){color = Color.rgb(150, 255, 150);}
                else if (type == 2){color = Color.rgb(200, 200, 200);}
                else if (type == 3){color = Color.rgb(0, 255, 0);}
                else if (type == 4){color = Color.rgb(80, 255, 0);}
                render.generate(x, y, color);
            }
        }
        render.finish();

    }

}
