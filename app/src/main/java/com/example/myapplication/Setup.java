package com.example.myapplication;

import static com.example.myapplication.Tile.BUSH;
import static com.example.myapplication.Tile.EMPTY;
import static com.example.myapplication.Tile.GRASS;
import static com.example.myapplication.Tile.STONE;
import static com.example.myapplication.Tile.TREE;

import android.graphics.Color;
import android.widget.ImageView;

public class Setup { //Настройка карты (класс нужен чтобы все не развалилось) + все геттеры обьектов

    public int width; //статы и вьюшка
    public int height;
    public int screenWidth;
    public int screenHeight;
    ImageView map;

    MapData mapData; //все обьекты
    Render render;
    Generator generator;
    Character character;
    Colors colors;

    Menu menu;
    public Setup (int w, int h, int scrw, int scrh, ImageView m){ //получаю все что надо
        width = w;
        height = h;
        screenWidth = scrw;
        screenHeight = scrh;
        map = m;
    }

    public void build(){
        character = new Character(MainActivity.Context(),  width/2, height/2); //создание персонажа
        generator = new Generator(); //генератора
        mapData = new MapData(width, height); //мапдаты
        colors = new Colors(width, height); //цветовой палитры
        menu = new Menu(); //менюшки
        render = new Render(MainActivity.Context(), map, width, height); //и рендерера
        render.setup(); //подготовОчка
        for (int x = 39; x >= 0; x--){
            for (int y = 59; y >= 0; y--){
                Tile type = generator.generate();
                mapData.floor[x][y] = type;
            }
        }
        int x = -1;
        int y = -1;
        for (int scrx = character.x - (screenWidth/2); scrx <= character.x + (screenWidth/2); scrx++){ //потайловая генерация изображения
            x++;
            for (int scry = character.y - (screenHeight/2); scry <= character.y + (screenHeight/2); scry++){
                y++;
                mapData.type[x][y] = mapData.floor[scrx][scry];
                int color = 0; //содержит цифровой код цвета (RGB)
                switch (mapData.type[x][y]){
                    case EMPTY: color = colors.empty; break;//пустой тайл
                    case TREE: color = colors.tree; break;//дерево
                    case STONE: color = colors.stone; break;//камень
                    case GRASS: color = colors.grass; break;//трава
                    case BUSH: color = colors.bush; break;//куст
                }
                render.generate(x , y, color);
                colors.colorMap[x][y] = color;
            }
        }

        character.setReferences();
        character.create(); //подготовОчка
        int color = colors.player; //PLAYER
        render.generate(character.x, character.y, color); //ставлю персонажа
        render.finish(); //отображаю биткарту

    }

    public void moveup (){



    }
    public void movedown (){



    }
    public void moveright (){



    }
    public void moveleft (){



    }
    public MapData getMapData() { //отдаю мапдату
        return mapData;
    }
    public Character getCharacter(){//отдаю ярика
        return character;
    }
    public Render getRender () {
        return render;
    }
    public Colors getColors (){
        return colors;
    }
    public Menu getMenu () {
        return menu;
    }

}
