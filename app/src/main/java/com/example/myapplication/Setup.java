package com.example.myapplication;

import static com.example.myapplication.Tile.BUSH;
import static com.example.myapplication.Tile.EMPTY;
import static com.example.myapplication.Tile.GRASS;
import static com.example.myapplication.Tile.PLAYER;
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
    CameraMovementSetup cms;

    Menu menu;
    public Setup (int w, int h, int scrw, int scrh, ImageView m){ //получаю все что надо
        width = w;
        height = h;
        screenWidth = scrw;
        screenHeight = scrh;
        map = m;
    }

    public void build(){
        character = new Character(MainActivity.Context(),  screenWidth/2, screenHeight/2); //создание персонажа
        generator = new Generator(); //генератора
        mapData = new MapData(width, height, screenWidth, screenHeight); //мапдаты
        colors = new Colors(width, height); //цветовой палитры
        menu = new Menu(); //менюшки
        cms = new CameraMovementSetup();
        render = new Render(MainActivity.Context(), map, screenWidth, screenHeight); //и рендерера
        render.setup(); //подготовОчка
        for (int x = 39; x >= 0; x--){ //генерация всей карты
            for (int y = 59; y >= 0; y--){
                Tile type = generator.generate();
                mapData.floor[x][y] = type;
            }
        }
        int x = -1;
        int y = -1;
        for (int scrx = character.x - (screenWidth/2); scrx <= character.x + (screenWidth/2); scrx++){ //потайловая генерация изображения
            x++;
            y = -1;
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
        for (int Y = 39; Y > 0; Y--){
            for(int X = 0; X <= 20; X++){
                mapData.type[X][Y] = mapData.type[X][Y - 1];
                int color = 0;
                switch (mapData.type[X][Y]){
                    case EMPTY: color = colors.empty; break;
                    case TREE: color = colors.tree; break;
                    case STONE: color = colors.stone; break;
                    case GRASS: color = colors.grass; break;
                    case BUSH: color = colors.bush; break;
                }
                render.generate(X , Y, color);
                colors.colorMap[X][Y] = color;
            }
        }
        for (int X = 0; X <= 20; X++){
            mapData.type[X][0] = mapData.floor[mapData.topLeftCorner[0] + X][mapData.topLeftCorner[1] - 1];
            int color = 0;
            switch (mapData.type[X][0]){
                case EMPTY: color = colors.empty; break;
                case TREE: color = colors.tree; break;
                case STONE: color = colors.stone; break;
                case GRASS: color = colors.grass; break;
                case BUSH: color = colors.bush; break;
            }
            render.generate(X, 0, color);
            colors.colorMap[X][0] = color;
        }
        mapData.topLeftCorner[1]--;
        mapData.bottomRightCorner[1]--;
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.player);
    }
    public void movedown (){
        for (int Y = 0; Y < 40; Y++){
            for(int X = 0; X <= 20; X++){
                mapData.type[X][Y] = mapData.type[X][Y + 1];
                int color = 0;
                switch (mapData.type[X][Y]){
                    case EMPTY: color = colors.empty; break;//пустой тайл
                    case TREE: color = colors.tree; break;//дерево
                    case STONE: color = colors.stone; break;//камень
                    case GRASS: color = colors.grass; break;//трава
                    case BUSH: color = colors.bush; break;//куст
                }
                render.generate(X , Y, color);
                colors.colorMap[X][Y] = color;
            }
        }
        for (int X = 0; X <= 20; X++){
            mapData.type[X][40] = mapData.floor[mapData.topLeftCorner[0] + X][mapData.bottomRightCorner[1] + 1];
            int color = 0;
            switch (mapData.type[X][40]){
                case EMPTY: color = colors.empty; break;//пустой тайл
                case TREE: color = colors.tree; break;//дерево
                case STONE: color = colors.stone; break;//камень
                case GRASS: color = colors.grass; break;//трава
                case BUSH: color = colors.bush; break;//куст
            }
            render.generate(X , 40, color);
            colors.colorMap[X][40] = color;
        }
        mapData.topLeftCorner[1]++;
        mapData.bottomRightCorner[1]++;
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.player);
    }
    public void moveright (){
        for (int X = 0; X < 20; X++){
            for (int Y = 0; Y <= 40; Y++){
                mapData.type[X][Y] = mapData.type[X + 1][Y];
                int color = 0;
                switch (mapData.type[X][Y]){
                    case EMPTY: color = colors.empty; break;//пустой тайл
                    case TREE: color = colors.tree; break;//дерево
                    case STONE: color = colors.stone; break;//камень
                    case GRASS: color = colors.grass; break;//трава
                    case BUSH: color = colors.bush; break;//куст
                }
                render.generate(X , Y, color);
                colors.colorMap[X][Y] = color;
            }
        }
        for (int Y = 0; Y <= 40; Y++){
            mapData.type[20][Y] = mapData.floor[mapData.bottomRightCorner[0] + 1][mapData.topLeftCorner[1] + Y];
            int color = 0;
            switch (mapData.type[20][Y]){
                case EMPTY: color = colors.empty; break;//пустой тайл
                case TREE: color = colors.tree; break;//дерево
                case STONE: color = colors.stone; break;//камень
                case GRASS: color = colors.grass; break;//трава
                case BUSH: color = colors.bush; break;//куст
            }
            render.generate(20 , Y, color);
            colors.colorMap[20][Y] = color;
        }
        mapData.topLeftCorner[0]++;
        mapData.bottomRightCorner[0]++;
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.player);
    }
    public void moveleft (){
        for (int X = 19; X > 0; X--){
            for (int Y = 0; Y <= 40; Y++){
                mapData.type[X][Y] = mapData.type[X - 1][Y];
                int color = 0;
                switch (mapData.type[X][Y]){
                    case EMPTY: color = colors.empty; break;//пустой тайл
                    case TREE: color = colors.tree; break;//дерево
                    case STONE: color = colors.stone; break;//камень
                    case GRASS: color = colors.grass; break;//трава
                    case BUSH: color = colors.bush; break;//куст
                }
                render.generate(X , Y, color);
                colors.colorMap[X][Y] = color;
            }
        }
        for (int Y = 0; Y <= 40; Y++){
            mapData.type[0][Y] = mapData.floor[mapData.topLeftCorner[0] - 1][mapData.topLeftCorner[1] + Y];
            int color = 0;
            switch (mapData.type[0][Y]){
                case EMPTY: color = colors.empty; break;//пустой тайл
                case TREE: color = colors.tree; break;//дерево
                case STONE: color = colors.stone; break;//камень
                case GRASS: color = colors.grass; break;//трава
                case BUSH: color = colors.bush; break;//куст
            }
            render.generate(0 , Y, color);
            colors.colorMap[0][Y] = color;
        }
        mapData.topLeftCorner[0]--;
        mapData.bottomRightCorner[0]--;
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.player);
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
    public CameraMovementSetup getCameraMovementSetup() {
        return cms;
    }
}
