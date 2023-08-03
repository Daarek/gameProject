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
        colors.handleBitmaps();
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
                int[][] color = new int[21][21];
                switch (mapData.type[x][y]){
                    case EMPTY: color = colors.emptyImage; break;//пустой тайл
                    case TREE: color = colors.treeImage; break;//дерево
                    case STONE: color = colors.stoneImage; break;//камень
                    case GRASS: color = colors.grassImage; break;//трава
                    case BUSH: color = colors.bushImage; break;//куст
                }
                render.generate(x , y, color);
                colors.colorMap[x][y] = color;
            }
        }
        for (int i = 0; i < mapData.amount.length; i++){ //генерирую ресурсы на карте
            for (int j = 0; j < mapData.amount[i].length; j++){
                mapData.amount[i][j] = generator.generateResource(mapData.floor[i][j]);
            }
        }
        character.setReferences();
        character.create(); //подготовОчка
        int[][] color = colors.playerImage; //PLAYER
        render.generate(character.x, character.y, color); //ставлю персонажа
        render.finish(); //отображаю биткарту
        moveup(); //легендарные костыли
        movedown();

    }
    public void moveup () {
        mapData.topLeftCorner[1]--;
        mapData.bottomRightCorner[1]--;
        for (int i = 0; i <= 20; i++){
            for(int j = 0; j <= 40; j++){
                mapData.type[i][j] = mapData.floor[mapData.topLeftCorner[0] + i][mapData.topLeftCorner[1] + j];
            }
        }
        render.update();
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.playerImage);
    }
    public void movedown () {
        mapData.topLeftCorner[1]++;
        mapData.bottomRightCorner[1]++;
        for (int i = 0; i <= 20; i++){
            for(int j = 0; j <= 40; j++){
                mapData.type[i][j] = mapData.floor[mapData.topLeftCorner[0] + i][mapData.topLeftCorner[1] + j];
            }
        }
        render.update();
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.playerImage);
    }
    public void moveleft () {
        mapData.topLeftCorner[0]--;
        mapData.bottomRightCorner[0]--;
        for (int i = 0; i <= 20; i++){
            for(int j = 0; j <= 40; j++){
                mapData.type[i][j] = mapData.floor[mapData.topLeftCorner[0] + i][mapData.topLeftCorner[1] + j];
            }
        }
        render.update();
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.playerImage);
    }
    public void moveright () {
        mapData.topLeftCorner[0]++;
        mapData.bottomRightCorner[0]++;
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 40; j++) {
                mapData.type[i][j] = mapData.floor[mapData.topLeftCorner[0] + i][mapData.topLeftCorner[1] + j];
            }
        }
        render.update();
        character.lastTile = mapData.type[character.x][character.y];
        mapData.type[character.x][character.y] = PLAYER;
        mapData.type[character.x][character.y] = character.lastTile;
        render.generate(character.x, character.y, colors.playerImage);
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
