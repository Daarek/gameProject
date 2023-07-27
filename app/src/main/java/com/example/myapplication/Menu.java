package com.example.myapplication;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu {
    AlertDialog menu;
    AlertDialog.Builder menuBuilder;
    AlertDialog inventory;
    AlertDialog.Builder inventoryBuilder;
    Button inventoryButton;
    TextView amountWood;
    TextView amountStone;
    TextView amountThread;
    TextView amountBerries;
    MapData mapData;
    Setup setup;
    Character character;

    public Menu () {
    }

    public void setup () {
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();
        character = setup.getCharacter();

        menuBuilder = new AlertDialog.Builder(MainActivity.Context());
        inventoryBuilder = new AlertDialog.Builder(MainActivity.Context());
        LayoutInflater menuInflater = LayoutInflater.from(MainActivity.Context());
        View menuView = menuInflater.inflate(R.layout.menu, null);
        LayoutInflater inventoryInflater = LayoutInflater.from(MainActivity.Context());
        View inventoryView = inventoryInflater.inflate(R.layout.inventory, null);
        menuBuilder.setView(menuView);
        inventoryBuilder.setView(inventoryView);
        menu = menuBuilder.create();
        inventory = inventoryBuilder.create();
        inventoryButton = menuView.findViewById(R.id.inventory);

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

    }


    public void alert () {
        menu.show();
    }
}

