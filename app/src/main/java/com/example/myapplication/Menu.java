package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.zip.Inflater;

public class Menu {
    AlertDialog menu;
    AlertDialog.Builder menuBuilder;
    AlertDialog interaction;
    AlertDialog.Builder interactionBuilder;
    Button interact;
    TextView amount;
    TextView type;
    MapData mapData;
    Setup setup;

    public Menu () {

    }

    public void setup () {
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();

        menuBuilder = new AlertDialog.Builder(MainActivity.Context());
        interactionBuilder = new AlertDialog.Builder(MainActivity.Context());
        LayoutInflater menuInflater = LayoutInflater.from(MainActivity.Context());
        View menuView = menuInflater.inflate(R.layout.menu, null);
        LayoutInflater interactionInflater = LayoutInflater.from(MainActivity.Context());
        View interactionView = interactionInflater.inflate(R.layout.interaction, null);
        menuBuilder.setView(menuView);
        interactionBuilder.setView(interactionView);
        menu = menuBuilder.create();
        interaction = interactionBuilder.create();
        interact = menuView.findViewById(R.id.Interact);
        amount = interactionView.findViewById(R.id.amountView);
        type = interactionView.findViewById(R.id.typeView);
        interact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText(Integer.toString(mapData.amount[mapData.playerPos[0]][mapData.playerPos[1]]));
                type.setText(mapData.floor[mapData.playerPos[0]][mapData.playerPos[1]].toString());
                interaction.show();
            }
        });

    }


    public void alert () {
        menu.show();
    }
}

