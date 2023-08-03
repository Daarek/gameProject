package com.example.myapplication;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu {
    public AlertDialog menu;
    private AlertDialog.Builder menuBuilder;
    private AlertDialog inventory;
    private AlertDialog.Builder inventoryBuilder;
    private AlertDialog mine;
    private AlertDialog.Builder mineBuilder;
    private AlertDialog craft;
    private AlertDialog.Builder craftBuilder;
    private AlertDialog building;
    private AlertDialog.Builder buildingBuilder;
    private Button inventoryButton;
    private Button mineButton;
    private Button craftButton;
    private Button buildingButton;
    private Button mCurrent;
    private Button mTop;
    private Button mBottom;
    private Button mRight;
    private Button mLeft;
    private Button craftPlanks;
    private Button craftStrings;
    private Button craftSlabs;
    private Button buildWall;
    private TextView amountWood;
    private TextView amountStone;
    private TextView amountThread;
    private TextView amountBerries;
    private TextView amountPlanks;
    private TextView amountStrings;
    private TextView amountSlabs;
    private MapData mapData;
    private Setup setup;
    private Character character;
    private Render render;

    public View menuView;

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
        craftBuilder = new AlertDialog.Builder(MainActivity.Context());
        buildingBuilder = new AlertDialog.Builder(MainActivity.Context());

        LayoutInflater menuInflater = LayoutInflater.from(MainActivity.Context());
        View menuView = menuInflater.inflate(R.layout.menu, null);
        LayoutInflater inventoryInflater = LayoutInflater.from(MainActivity.Context());
        View inventoryView = inventoryInflater.inflate(R.layout.inventory, null);
        LayoutInflater mineInflater = LayoutInflater.from(MainActivity.Context());
        View mineView = mineInflater.inflate(R.layout.mine, null);
        LayoutInflater craftInflater = LayoutInflater.from(MainActivity.Context());
        View craftView = craftInflater.inflate(R.layout.craft, null);
        LayoutInflater buildinginflater = LayoutInflater.from(MainActivity.Context());
        View buildingView = buildinginflater.inflate(R.layout.building, null);

        menuBuilder.setView(menuView);
        inventoryBuilder.setView(inventoryView);
        mineBuilder.setView(mineView);
        craftBuilder.setView(craftView);
        buildingBuilder.setView(buildingView);

        menu = menuBuilder.create();
        inventory = inventoryBuilder.create();
        mine = mineBuilder.create();
        craft = craftBuilder.create();
        building = buildingBuilder.create();

        inventoryButton = menuView.findViewById(R.id.inventory);
        mineButton = menuView.findViewById(R.id.mine);
        craftButton = menuView.findViewById(R.id.craft);
        buildingButton = menuView.findViewById(R.id.building);
        mCurrent = mineView.findViewById(R.id.mCurrent);
        mTop = mineView.findViewById(R.id.mTop);
        mBottom = mineView.findViewById(R.id.mDown);
        mRight = mineView.findViewById(R.id.mRight);
        mLeft = mineView.findViewById(R.id.mLeft);
        craftPlanks = craftView.findViewById(R.id.planksCraft);
        craftStrings = craftView.findViewById(R.id.stringsCraft);
        craftSlabs = craftView.findViewById(R.id.slabsCraft);
        buildWall = buildingView.findViewById(R.id.wall);

        amountWood = inventoryView.findViewById(R.id.amountWood);
        amountStone = inventoryView.findViewById(R.id.amountStone);
        amountThread = inventoryView.findViewById(R.id.amountThread);
        amountBerries = inventoryView.findViewById(R.id.amountBerries);
        amountPlanks = inventoryView.findViewById(R.id.amountPlanks);
        amountStrings = inventoryView.findViewById(R.id.amountStrings);
        amountSlabs = inventoryView.findViewById(R.id.amountSlabs);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountWood.setText(Integer.toString(character.inventory.wood));
                amountStone.setText(Integer.toString(character.inventory.stone));
                amountThread.setText(Integer.toString(character.inventory.thread));
                amountBerries.setText(Integer.toString(character.inventory.berries));
                amountPlanks.setText(Integer.toString(character.inventory.planks));
                amountStrings.setText(Integer.toString(character.inventory.strings));
                amountSlabs.setText(Integer.toString(character.inventory.slabs));
                menu.hide();
                inventory.show();
            }
        });
        mineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.hide();
                mine.show();
            }
        });
        craftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.hide();
                craft.show();
            }
        });
        buildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.hide();
                building.show();
            }
        });
        mCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                switch (mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]]){
                    case EMPTY: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case WALL: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]);toast.show(); character.inventory.wood += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.stone += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.berries += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]); toast.show(); character.inventory.thread += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.EMPTY; break;
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
                    case WALL: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.wood += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.stone += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.berries += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]); toast.show(); character.inventory.thread += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] - 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] - 1] = Tile.EMPTY; mapData.type[character.x][character.y - 1] = Tile.EMPTY; break;
                }
                render.update();
                render.generate(character.x, character.y, setup.colors.playerImage);
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
                    case WALL: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.wood += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.stone += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.berries += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]); toast.show(); character.inventory.thread += mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1]; mapData.amount[mapData.playerPos[0]][mapData.playerPos[1] + 1] = 0; mapData.floor[mapData.playerPos[0]][mapData.playerPos[1] + 1] = Tile.EMPTY; mapData.type[character.x][character.y + 1] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.playerImage);
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
                    case WALL: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.wood += mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.stone += mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.berries += mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]); toast.show(); character.inventory.thread += mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] - 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] - 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x - 1][character.y] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.playerImage);
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
                    case WALL: toast.setText("Тут нечего добыть"); toast.show(); break;
                    case TREE: toast.setText("Дерево + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.wood += mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case STONE: toast.setText("Камень + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.stone += mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case BUSH: toast.setText(" + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.berries += mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                    case GRASS: toast.setText("Волокно + " + mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]); toast.show(); character.inventory.thread += mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]]; mapData.amount[mapData.playerPos[0] + 1][mapData.playerPos[1]] = 0; mapData.floor[mapData.playerPos[0] + 1][mapData.playerPos[1]] = Tile.EMPTY; mapData.type[character.x + 1][character.y] = Tile.EMPTY; break;
                }

                render.update();
                render.generate(character.x, character.y, setup.colors.playerImage);
                render.finish();
                mine.hide();
                menu.hide();
            }
        });
        craftPlanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                if (character.inventory.wood >= 2){
                    character.inventory.wood -= 2;
                    character.inventory.planks += 1;
                    toast.setText("Успешно");
                } else {
                    toast.setText("Нет ресурсов");
                }
                toast.show();
            }
        });
        craftStrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                if (character.inventory.thread >= 1){
                    character.inventory.thread -= 1;
                    character.inventory.strings += 1;
                    toast.setText("Успешно");
                } else {
                    toast.setText("Нет ресурсов");
                }
                toast.show();
            }
        });
        craftSlabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                if (character.inventory.stone >= 4){
                    character.inventory.stone -= 4;
                    character.inventory.slabs += 1;
                    toast.setText("Успешно");
                } else {
                    toast.setText("Нет ресурсов");
                }
                toast.show();
            }
        });
        buildWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.Context());
                if (character.inventory.slabs >= 1 && character.inventory.planks >= 2 && character.inventory.strings >= 8){
                    character.inventory.slabs -= 1;
                    character.inventory.planks -= 2;
                    character.inventory.strings -= 8;
                    mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]] = Tile.WALL;
                    toast.setText("Успешно");
                    toast.show();
                } else {
                    toast.setText("Нет ресурсов");
                    toast.show();
                }
            }
        });

    }
    public void alert () {
        menu.show();
    }
}