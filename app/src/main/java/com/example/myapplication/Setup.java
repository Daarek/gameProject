package com.example.myapplication;

import android.graphics.Color;
import android.widget.ImageView;

public class Setup { //Настройка карты (класс нужен чтобы все не развалилось) + все геттеры обьектов

    public int width; //статы и вьюшка
    public int height;
    ImageView map;

    MapData mapData; //все обьекты
    Render render;
    Generator generator;
    Character character;
    public Setup (int w, int h, ImageView m){ //получаю все что надо
        width = w;
        height = h;
        map = m;
    }

    public void build(){
        character = new Character(MainActivity.Context(), width/2, height/2); //создание персонажа
        generator = new Generator(); //генератора
        mapData = new MapData(width, height); //мапдаты
        render = new Render(MainActivity.Context(), map, width, height); //и рендерера
        render.setup(); //подготовОчка

        for (int x = width - 1; x >= 0; x-- ){ //потайловая генерация
            for (int y = height - 1; y >= 0; y--){
                Tile type = generator.generate();
                int color = 0;
                mapData.type[x][y] = type;
                switch (type){
                    case EMPTY: color = Color.rgb(200, 200, 200); break;//пустой тайл
                    case TREE: color = Color.rgb(150, 255, 150); break;//дерево
                    case STONE: color = Color.rgb(100, 100, 100); break;//камень
                    case GRASS: color = Color.rgb(0, 200, 0); break;//трава
                    case BUSH: color = Color.rgb(80, 180, 0); break;//куст
                }
                render.generate(x , y, color);
            }
        }
        character.setReferences();
        character.create(); //подготовОчка
        int color = Color.rgb(0, 0, 255); //PLAYER
        render.generate(character.x, character.y, color); //ставлю персонажа
        render.finish(); //отображаю биткарту

    }
    public MapData getMapData() { //отдаю мапдату
        return mapData;
    }
    public Character getCharacter(){//отдаю ярика
        return character;
    }

}
