package com.alannnc.transitionsapp.transitionsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ViewGroup view;
    private ImageView circle;
    private boolean sizeChanged;
    private int savedWidth;
    private boolean positionChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayout();
        Button change_size = (Button) findViewById(R.id.change_size);
        change_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLayout();
            }
        });

        Button change_position = (Button) findViewById(R.id.change_position);
        change_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePosition();
            }
        });
    }

    private void setupLayout() {
        circle = (ImageView) findViewById(R.id.circle);
        view = (ViewGroup) findViewById(R.id.root_view);
    }

    private void changeLayout() {
        TransitionManager.beginDelayedTransition(view);

        ViewGroup.LayoutParams params = circle.getLayoutParams();
        if (sizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        circle.setLayoutParams(params);
    }

    private void changePosition() {
        TransitionManager.beginDelayedTransition(view);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) circle.getLayoutParams();
        Log.d("DEV", String.valueOf(lp));
        if (positionChanged) {
            lp.gravity = Gravity.CENTER;
        } else {
            lp.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        circle.setLayoutParams(lp);
    }
}
