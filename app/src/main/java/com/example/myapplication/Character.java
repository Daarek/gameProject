package com.example.myapplication;

import static com.example.myapplication.Tile.PLAYER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

public class Character extends View { //мэин хиро
    public int x; // x и y на локальной карте
    public int y;
    public MapData mapData; //данные о карте
    public double shift; //во сколько раз  ширина экрана меньше высоты
    public int centerX; //координаты центра экрана
    public int centerY;
    public int width; //данные о экране
    public int height;
    Setup setup; //сетапер
    Render render; //рендерер
    Colors colors; //цвета
    Menu menu;
    AlertDialog alert;
    Tile lastTile;

    public Character (Context context, int X, int Y) {
        super(context);
        x = X;
        y = Y;
        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        width = metrics.widthPixels; //получаю размеры экрана
        height = metrics.heightPixels;
        centerX = width/2; //получаю координаты центра
        centerY = height/2;
        shift = (double) width/height; //получаю шифт (ширина/высота) (всегда между 0 и 1)

    }
    public void setReferences(){
        setup = MainActivity.getSetup(); //получаю сетапер
        mapData = setup.getMapData(); //получаю мапдату
        render = setup.getRender(); //получаю рендерер
        colors = setup.getColors();
        menu = setup.getMenu();
        menu.setup();

    }
    public void create(){
        lastTile = mapData.type[x][y];
        mapData.type[x][y] = PLAYER; //координаты игрока на битмапе
    }
    public void move(double actualX, double eventY) {
        double actualY = eventY * shift;
        double actualHeight = height * shift;
        double fwp = width * 0.4;
        double swp = width * 0.6;
        double fhp = actualHeight * 0.4;
        double shp = actualHeight * 0.6;
        if ((fwp < actualX && actualX < swp) && (fhp < actualY && actualY < shp)){ //проверяет, было ли нажатие в центре
            menu.alert();
        } else {
            if (actualX / actualY > 1) { //Верх или право
                if (actualX + actualY > width) { //право
                    mapData.type[x][y] = lastTile;
                    render.generate(x, y, colors.colorMap[x][y]);
                    x++;
                    lastTile = mapData.type[x][y];
                    mapData.type[x][y] = PLAYER;
                    mapData.playerPos[0]++;
                    render.generate(x, y, colors.player);
                } else {//Верх
                    mapData.type[x][y] = lastTile;
                    render.generate(x, y, colors.colorMap[x][y]);
                    y--;
                    lastTile = mapData.type[x][y];
                    mapData.type[x][y] = PLAYER;
                    mapData.playerPos[1]--;
                    render.generate(x, y, colors.player);
                }
            } else { //низ или лево
                if (actualX + actualY > width) {//низ
                    mapData.type[x][y] = lastTile;
                    render.generate(x, y, colors.colorMap[x][y]);
                    y++;
                    lastTile = mapData.type[x][y];
                    mapData.type[x][y] = PLAYER;
                    mapData.playerPos[1]++;
                    render.generate(x, y, colors.player);
                } else { //лево
                    mapData.type[x][y] = lastTile;
                    render.generate(x, y, colors.colorMap[x][y]);
                    x--;
                    lastTile = mapData.type[x][y];
                    mapData.type[x][y] = PLAYER;
                    mapData.playerPos[0]--;
                    render.generate(x, y, colors.player);
                }
            }
            render.finish();
        }
    }
    

}
