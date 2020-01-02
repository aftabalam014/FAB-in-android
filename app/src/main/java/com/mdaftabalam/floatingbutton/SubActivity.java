package com.mdaftabalam.floatingbutton;

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

public class SubActivity extends AppCompatActivity {

    FloatingActionButton FabBtn;
    WebView view;
    String ContentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        view = findViewById(R.id.textsub);
        ContentString = String.valueOf(Html.fromHtml("<![CDATA[<body style=\"text-align:justify;color:gray;background-color:black; \">"
                + getResources().getString(R.string.dummy_text) + "</body>]]>"));

        view.loadData(ContentString, "text/html", "utf-8");
        setupUI();
    }

    private void setupUI() {
        FabBtn = findViewById(R.id.button_fab);
        FabBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SubActivity.this, "Share Click!", Toast.LENGTH_SHORT).show();
                /*Intent as = new Intent(SubActivity.this, MainActivity.class);
                startActivity(as);*/

                // TODO issue: Rotate animation in pre-lollipop works only once, issue to be resolved!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setInterpolator(new FastOutSlowInInterpolator());
                    FabBtn.startAnimation(rotateAnimation);
                } else {
                    FabBtn.clearAnimation();
                    ViewPropertyAnimatorCompat animatorCompat = ViewCompat.animate(FabBtn);
                    animatorCompat.setDuration(500);
                    animatorCompat.setInterpolator(new FastOutSlowInInterpolator());
                    animatorCompat.rotation(180);
                    animatorCompat.start();
                }
            }
        });
    }
}