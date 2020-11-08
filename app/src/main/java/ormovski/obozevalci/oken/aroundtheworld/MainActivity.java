package ormovski.obozevalci.oken.aroundtheworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int NUM_PAGES = 18;
    private static int CIRCLE_WIDTH;
    private static final double ONE_PEACE_SIZE = 1.0 / (NUM_PAGES - 1);

    TextView stepTextView;
    View circle;
    ImageView leftShadow;
    View leftShadow2;
    View rightShadow;
    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 stepsPager = findViewById(R.id.steps_pager);
        stepTextView = findViewById(R.id.tv_step);
        circle = findViewById(R.id.circle);
        leftShadow = findViewById(R.id.shadow_left);
        leftShadow2 = findViewById(R.id.shadow_left_2);
        rightShadow = findViewById(R.id.shadow_right);

        final StepPageAdapter adapter = new StepPageAdapter(this);

        CIRCLE_WIDTH = circle.getLayoutParams().width;

        setEarthViewAndText(R.color.colorAccent, 0 * ONE_PEACE_SIZE);

        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.pop_out);
        circle.startAnimation(expandIn);

        song = MediaPlayer.create(this, R.raw.sound1);

        // Triggers page animation progress
        stepsPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                stepTextView.setText((position + 1) + "/" + NUM_PAGES);
                setMusic(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                StepPage first = adapter.stepPages.get(position);
                StepPage second = null;
                if (position < NUM_PAGES - 1) {
                    second = adapter.stepPages.get(position + 1);
                }

                first.setAnimationProgress(positionOffset);
                if (second != null) {
                    second.setAnimationProgress(1.0f - positionOffset);
                }

                double state = ((position) * ONE_PEACE_SIZE) + ONE_PEACE_SIZE * positionOffset;

                int color = R.color.MainCircle;
                switch (position) {
                    case 5: color =  R.color.Deamon;
                        break;
                    case 8: color = R.color.Underwater;
                        break;
                    case 15: color = R.color.Deamon2;
                }
                setEarthViewAndText(color, state);
            }

        });

        stepsPager.setAdapter(adapter);

    }

    private void setMusic(int sceenIndex) {
        int musicCode = R.raw.sound1;
        switch (sceenIndex) {
            case 0:
                musicCode = R.raw.sound1;
                break;
            case 2:
                musicCode = R.raw.sound_mechanolith;
                break;
            case 3:
                musicCode = R.raw.sound_legend;
                break;
            case (NUM_PAGES - 1):
                musicCode = R.raw.troll_sound;
                break;
            default:
                return;
        }
        if (song.isPlaying()) {
            song.stop();
        }
        song = MediaPlayer.create(this, musicCode);
        song.start();
    }

    private void setEarthViewAndText(int colorCode, final double state) {
        if (state < 0 || state > 1) {
            return;
        }
        Drawable background = circle.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable)background).getPaint().setColor(ContextCompat.getColor(this, colorCode));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable)background).setColor(ContextCompat.getColor(this, colorCode));
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable)background).setColor(ContextCompat.getColor(this, colorCode));
        }

        stepTextView.setTextColor(ContextCompat.getColor(this, colorCode));

        if (state >= 0.5) {
            leftShadow.setVisibility(View.INVISIBLE);
            leftShadow2.getLayoutParams().width = (CIRCLE_WIDTH/2);
            rightShadow.getLayoutParams().width = (int) ((CIRCLE_WIDTH/2) * ((state - 0.5) * 2));
            rightShadow.setVisibility(View.VISIBLE);
            rightShadow.requestLayout();
        } else {
            rightShadow.getLayoutParams().width = 0;
            leftShadow.setVisibility(View.VISIBLE);
            leftShadow.setBackgroundTintList(ContextCompat.getColorStateList(this, colorCode));
            leftShadow.getLayoutParams().width = (int) ((CIRCLE_WIDTH/2) * (1 - ((state) * 2)));
            leftShadow.requestLayout();
        }


    }

    // Class that handles pages and their animations
    private static class StepPageAdapter extends FragmentStateAdapter {

        public SparseArray<StepPage> stepPages = new SparseArray<>();

        public StepPageAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public StepPage createFragment(int position) {
            StepPage fragment = StepPage.newInstance(position);
            stepPages.put(position, fragment);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }

    }
}