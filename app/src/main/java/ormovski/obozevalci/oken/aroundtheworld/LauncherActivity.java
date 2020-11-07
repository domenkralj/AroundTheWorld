package ormovski.obozevalci.oken.aroundtheworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {

    ImageView earth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        earth = findViewById(R.id.earth);
        earth.startAnimation(AnimationUtils.loadAnimation(this, R.anim.heart_beat));

        // open main activity after 2500 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                },
                5000
        );
    }
}