package ormovski.obozevalci.oken.aroundtheworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NUM_PAGES = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 stepsPager = findViewById(R.id.steps_pager);

        final StepPageAdapter adapter = new StepPageAdapter(this);

        // Triggers page animation progress
        stepsPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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

            }
        });
        stepsPager.setAdapter(adapter);
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