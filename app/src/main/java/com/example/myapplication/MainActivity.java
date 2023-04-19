package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //さんとりお!

    ConstraintLayout layout;
    TextView map[][] = new TextView[20][20]; //массив текствьювов (когда узнал что оно работает чуть от радости не помер)
    public String mapStatus[][] = new String[20][20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        TextView setup[][] = new TextView[20][20]//разовая привязочная ссылка
        layout = findViewById(R.id.layout); // ищу экран (а где?)

        for (int x = 0; x < 20; x++) { //генерирую карту
            for (int y = 0; y < 20; y++){  //oh no
                mapStatus[x][y] = "0";
                map[x][y] = new TextView(this);
                map[x][y].setText(mapStatus[x][y]); //оно же сработает?
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT); //oh no x2
                params.setMargins(x * 10, y * 10, 0, 0); //пофиг на новую переменную, локальная же
                params.setId("id");//временный айди
                

                map[x][y].setLayoutParams(params); //делаем отступы и забываем про локал до следующей итерации
                View layoutSetup = getLayoutInflater().inflate(layout, null);//разовая лэйаут ссылка (надо)
                setup[x][y] = layoutSetup.findViewById(R.id.id);//ищу элемент на экране по айди
                params.setId("tile");//отвязываю айди
                map[x][y].setLayoutParams(params);
                map[x][y].setHeight(10);
                map[x][y].setWidth(10);
                setContentView(layoutSetup);//чуть не забыл отобразить (да как это бл еще не сломалось?)
            }
        }

        Character me = new Character(5, 6);//создаю персонажа
        movement(0, 5, 0, 6); //замена нулевых координат на 0 никому не повредит, всё равно не могу оставить пустым/null
        /*
        layout.setOnTouchListener(new View.OnTouchListener() { //обработка нажатий
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                double x = motionEvent.getX();
                double y = motionEvent.getY();

                double actX = x - me.layoutX;
                double actY = y - me.layoutY;

                if (actX > 0){ //справа

                    if (actY > 0){//справа-сверху
                        if (checkDiagonal(actX, actY)){movement();}
                    } else if (actY < 0) {//справа-снизу

                    } else { //справа

                    }

                } else if (actX < 0){//слева

                    if (actY > 0){//слева-сверху

                    } else if (actY < 0) {//слева-снизу

                    } else { //слева

                    }

                }else{//центр по горизонтали

                    if (actY > 0){//сверху

                    } else if (actY < 0) {//снизу

                    } else { //центр

                    }

                }

                return false;
            }
        }); */

    }

    public void movement (int prevX, int X, int prevY, int Y) { //чтобы двигаться
        mapStatus[prevX][prevY] = "0";
        mapStatus[X][Y] = "1";
        map[prevX][prevY].setText(mapStatus[prevX][prevY]);
        map[X][Y].setText(mapStatus[X][Y]);

    }

    public boolean checkDiagonal (double x, double y){

        double absX = Math.abs(x); //беру модули относительных координат
        double absY = Math.abs(y);

        if ( (absX/absY > -1.5) | (absX/absY < 1.5)){ //проверкаб насколько координаты "диагональны"
            return true;
        } else {
            return false;
        }

    }

}


