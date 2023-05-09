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
    public MapData mapData; //данные о карте
    public double shift; //во сколько раз  ширина экрана меньше высоты
    public int centerX; //координаты центра экрана
    public int centerY;
    public int width; //данные о экране
    public int height;
    Setup setup; //сетапер
    Render render; //рендерер
    Colors colors;
    public Character (Context context, int X, int Y) {
        super(context);
        x = X; //получаю стартовые координаты игрока
        y = Y;
        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        width = metrics.widthPixels; //получаю размеры экрана
        height = metrics.heightPixels;
        centerX = width/2; //получаю координаты центра
        centerY = height/2;
        shift = (double) width/height; //получаю шифт (ширина/высота)

    }
    public void setReferences(){
        setup = MainActivity.getSetup(); //получаю сетапер
        mapData = setup.getMapData(); //получаю мапдату
        render = setup.getRender(); //получаю рендерер
        colors = setup.getColors();

    }
    public void create(){
        mapData.type[x][y] = PLAYER; //координаты игрока на битмапе (поверх тайлов)
        mapData.playerPos[0] = x; //координаты игрока в .type
        mapData.playerPos[1] = y;
    }
    public void move(double actualX, double eventY) {
        double actualY = eventY * shift; //понадобится позже (наверное)
        if (actualX / actualY > 1) { //Верх или право
            if (actualX + actualY > width) { //право
                mapData.type[x][y] = null;
                render.generate(x, y, colors.colorMap[x][y]);
                x++;
                mapData.type[x][y] = PLAYER;
                mapData.playerPos[0]++;
                render.generate(x, y, colors.player);
            } else {//Верх
                mapData.type[x][y] = null;
                render.generate(x, y, colors.colorMap[x][y]);
                y--;
                mapData.type[x][y] = PLAYER;
                mapData.playerPos[1]--;
                render.generate(x, y, colors.player);
            }
        } else { //низ или лево
            if (actualX + actualY > width) {//низ
                mapData.type[x][y] = null;
                render.generate(x, y, colors.colorMap[x][y]);
                y++;
                mapData.type[x][y] = PLAYER;
                mapData.playerPos[1]++;
                render.generate(x, y, colors.player);
            } else { //лево
                mapData.type[x][y] = null;
                render.generate(x, y, colors.colorMap[x][y]);
                x--;
                mapData.type[x][y] = PLAYER;
                mapData.playerPos[0]--;
                render.generate(x, y, colors.player);
            }
        }
        render.finish();
    }

}
