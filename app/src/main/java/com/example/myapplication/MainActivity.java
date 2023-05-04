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
    static Context context;
    Setup setup;

    ImageView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        map = findViewById(R.id.map);

        setup = new Setup(20, 20, map);
        setup.build();


    }
    public static Context Context(){ //просто чтобы получить this к mainActivity
        return context;
    }

}


