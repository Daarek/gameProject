package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //さんとりお!

    LinearLayout layout = findViewById(R.id.layout); // ищу экран (а где?)
    TextView map[][] = new TextView[20][20]; //массив текствьювов (когда узнал что оно работает чуть от радости не помер)
    public int mapStatus[][] = new int[20][20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int x = 0; x < 20; x++) { //генерирую карту
            for (int y = 0; y < 20; y++){  //oh no
                mapStatus[x][y] = 0;
                map[x][y] = new TextView(this);
                map[x][y].setText(mapStatus[x][y]); //оно же сработает?
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); //oh no x2
                params.setMargins(x * 10, y * 10, 0, 0); //пофиг на новую переменную, локальная же
                map[x][y].setLayoutParams(params); //делаем отступы и забываем про локал до следующей итерации
                map[x][y].setHeight(10);
                map[x][y].setWidth(10);
                layout.addView(map[x][y]);//чуть не забыл отобразить (да как это бл еще не сломалось?)
            }
        }

        Character me = new Character(5, 6);//создаю персонажа
        movement(0, 5, 0, 6); //замена нулевых координат на 0 никому не повредит, всё равно не могу оставить пустым/null



    }

    public void movement (int prevX, int X, int prevY, int Y) { //чтобы двигаться
        mapStatus[prevX][prevY] = 0;
        mapStatus[X][Y] = 1;
        map[prevX][prevY].setText(mapStatus[prevX][prevY]);
        map[X][Y].setText(mapStatus[X][Y]);
    }

}