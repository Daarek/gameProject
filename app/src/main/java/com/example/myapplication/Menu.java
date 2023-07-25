package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Menu {
    AlertDialog menu;
    AlertDialog.Builder menuBuilder;
    AlertDialog interaction;
    AlertDialog.Builder interactionBuilder;
    Button interact;
    TextView amount;

    public Menu () {

    }

    public void setup () {
        menuBuilder = new AlertDialog.Builder(MainActivity.Context());
        menuBuilder.setView(R.layout.menu);
        menu = menuBuilder.create();
        interactionBuilder = new AlertDialog.Builder(MainActivity.Context());
        interactionBuilder.setView(R.layout.interaction);
        interaction = interactionBuilder.create();
        //доделать тут
    }

    public void alert () {
        menu.show();
    }
}
