package com.mdaftabalam.floatingbutton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        btnFab = findViewById(R.id.btnFloatingAction);
        btnFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add Click!", Toast.LENGTH_SHORT).show();
                Intent as = new Intent(MainActivity.this, SubActivity.class);
                startActivity(as);

                // TODO issue: Rotate animation in pre-lollipop works only once, issue to be resolved!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setInterpolator(new FastOutSlowInInterpolator());
                    btnFab.startAnimation(rotateAnimation);
                } else {
                    btnFab.clearAnimation();
                    ViewPropertyAnimatorCompat animatorCompat = ViewCompat.animate(btnFab);
                    animatorCompat.setDuration(500);
                    animatorCompat.setInterpolator(new FastOutSlowInInterpolator());
                    animatorCompat.rotation(180);
                    animatorCompat.start();
                }
            }
        });
    }
}