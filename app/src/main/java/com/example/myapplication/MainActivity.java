package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity { //さんとりお!
    static Context context;
    static Setup setup;

    ImageView map; //ссылки на элементы
    ConstraintLayout layout;
    Character character; //получу персонажа позже

    public static Resources context() {//другой контекст для Drawable

        return null;
    }
    public static Setup getSetup(){
        return setup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;//контекст в context()
        map = findViewById(R.id.map);//получаю вьюшку

        setup = new Setup(20, 20, map);//настраиваю сетап
        setup.build();
        character = setup.getCharacter(); //получаю ссылку на обьект персонажа
        layout = findViewById(R.id.layout);//получаю лейаут и ставлю обработку тапов
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                character.move(motionEvent.getX(), motionEvent.getY()); //взываю к богам (передвижение)
                return false;
            }
        });


    }
    public static Context Context(){ //просто чтобы получить this для Render
        return context;
    }

}


