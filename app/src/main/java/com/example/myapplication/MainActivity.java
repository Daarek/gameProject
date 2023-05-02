package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity { //さんとりお!

    TextView map[][] = new TextView[20][20]; //массив текствьювов (когда узнал что оно работает чуть от радости не помер)
    public String mapStatus[][] = new String[20][20];
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

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

    public void movement (int prevX, int X, int prevY, int Y) { //чтобы двигаться, переделать!!!
        mapStatus[prevX][prevY] = "0";
        mapStatus[X][Y] = "1";
        map[prevX][prevY].setText(mapStatus[prevX][prevY]);
        map[X][Y].setText(mapStatus[X][Y]);

    }

    public boolean checkDiagonal (double x, double y){

        double absX = Math.abs(x); //беру модули относительных координат
        double absY = Math.abs(y);

        if ( (absX/absY > -1.5) | (absX/absY < 1.5)){ //проверка, насколько координаты "диагональны"
            return true;
        } else {
            return false;
        }

    }

    public static Context Context(){ //просто чтобы получить статический this к mainActivity
        return context;
    }

}


