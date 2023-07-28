package com.example.myapplication;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu {
    private AlertDialog menu;
    private AlertDialog.Builder menuBuilder;
    private AlertDialog inventory;
    private AlertDialog.Builder inventoryBuilder;
    private AlertDialog mine;
    private AlertDialog.Builder mineBuilder;
    private Button inventoryButton;
    private Button mineButton;
    private Button mCurrent;
    private Button mTop;
    private Button mBottom;
    private Button mRight;
    private Button mLeft;
    private TextView amountWood;
    private TextView amountStone;
    private TextView amountThread;
    private TextView amountBerries;
    private MapData mapData;
    private Setup setup;
    private Character character;
    private Render render;

    public Menu () {
    }

    public void setup () {
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();
        character = setup.getCharacter();
        render = setup.getRender();

        menuBuilder = new AlertDialog.Builder(MainActivity.Context());
        inventoryBuilder = new AlertDialog.Builder(MainActivity.Context());
        mineBuilder = new AlertDialog.Builder(MainActivity.Context());

        LayoutInflater menuInflater = LayoutInflater.from(MainActivity.Context());
        View menuView = menuInflater.inflate(R.layout.menu, null);
        LayoutInflater inventoryInflater = LayoutInflater.from(MainActivity.Context());
        View inventoryView = inventoryInflater.inflate(R.layout.inventory, null);
        LayoutInflater mineInflater = LayoutInflater.from(MainActivity.Context());
        View mineView = mineInflater.inflate(R.layout.mine, null);

        menuBuilder.setView(menuView);
        inventoryBuilder.setView(inventoryView);
        mineBuilder.setView(mineView);

        menu = menuBuilder.create();
        inventory = inventoryBuilder.create();
        mine = mineBuilder.create();

        inventoryButton = menuView.findViewById(R.id.inventory);
        mineButton = menuView.findViewById(R.id.mine);
        mCurrent = mineView.findViewById(R.id.mCurrent);
        mTop = mineView.findViewById(R.id.mTop);
        mBottom = mineView.findViewById(R.id.mDown);
        mRight = mineView.findViewById(R.id.mRight);
        mLeft = mineView.findViewById(R.id.mLeft);

        amountWood = inventoryView.findViewById(R.id.amountWood);
        amountStone = inventoryView.findViewById(R.id.amountStone);
        amountThread = inventoryView.findViewById(R.id.amountThread);
        amountBerries = inventoryView.findViewById(R.id.amountBerries);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountWood.setText(Integer.toString(character.inventory.wood));
                amountStone.setText(Integer.toString(character.inventory.stone));
                amountThread.setText(Integer.toString(character.inventory.thread));
                amountBerries.setText(Integer.toString(character.inventory.berries));
                inventory.show();
            }
        });
        mineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mine.show();
            }
        });
        mCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]);toast.show(); character.inventory.wood =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.stone =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.berries =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.thread =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                }
                mine.hide();
                menu.hide();
            }

        });
        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.wood =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.stone =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.berries =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.thread =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                }
                render.update();
                render.generate(character.x, character.y, setup.colors.player);
                render.finish();
                mine.hide();
                menu.hide();
            }
        });
        mBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.wood =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.stone =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.berries =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.thread =+ mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.player);
                render.finish();
                mine.hide();
                menu.hide();
            }
        });
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.wood =+ mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.stone =+ mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.berries =+ mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.thread =+ mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.player);
                render.finish();
                mine.hide();
                menu.hide();
            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.wood =+ mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.stone =+ mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.berries =+ mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.thread =+ mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.player);
                render.finish();
                mine.hide();
                menu.hide();
            }
        });

    }


    public void alert () {
        menu.show();
    }
}

