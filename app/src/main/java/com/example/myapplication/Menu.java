package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Menu {
    AlertDialog alert;
    AlertDialog.Builder alertBuilder;

    public Menu () {

    }

    public void setup () {
        alertBuilder = new AlertDialog.Builder(MainActivity.Context());
        alertBuilder.setView(R.layout.menu);
        alert = alertBuilder.create();
    }

    public void alert () {
        alert.show();
    }
}
