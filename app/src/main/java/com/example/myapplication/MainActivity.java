package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Splash screen display time in milliseconds
    private static final int SPLASH_DISPLAY_TIME = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find the ImageView
        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Apply fade-in animation
        AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1500); // Adjust duration as needed
        logoImageView.startAnimation(fadeIn);

        // Make the ImageView visible after animation
        fadeIn.setAnimationListener(new AlphaAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                logoImageView.setVisibility(ImageView.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Use a Handler to delay the opening of the LoginActivity
                new Handler().postDelayed(() -> {
                    // Create an Intent to start the LoginActivity
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                    // Close the splash activity
                    finish();
                }, SPLASH_DISPLAY_TIME);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
