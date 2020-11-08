package ormovski.obozevalci.oken.aroundtheworld;

import android.os.Bundle;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepPage extends Fragment {

    private static final String PAGE = "page";

    private int page;
    private MotionLayout view;

    public StepPage() {
        // Required empty public constructor
    }

    public static StepPage newInstance(int page) {
        StepPage fragment = new StepPage();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(PAGE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (page == 17) {
            view.transitionToEnd();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int layoutId;
        switch (page) {
            case 1: layoutId = R.layout.step_two_screen;
                    break;
            case 2: layoutId = R.layout.step_three_screen;
                    break;
            case 3: layoutId = R.layout.step_four_screen;
                    break;
            case 4: layoutId = R.layout.step_five_screen;
                    break;
            case 5: layoutId = R.layout.step_six_screen;
                    break;
            case 6: layoutId = R.layout.step_seven_screen;
                    break;
            case 7: layoutId = R.layout.step_eight_screen;
                    break;
            case 8: layoutId = R.layout.step_nine_screen;
                    break;
            case 9: layoutId = R.layout.step_ten_screen;
                    break;
            case 10: layoutId = R.layout.step_eleven_screen;
                    break;
            case 11: layoutId = R.layout.step_twelve_screen;
                    break;
            case 12: layoutId = R.layout.step_thirteen_screen;
                    break;
            case 13: layoutId = R.layout.step_fourteen_screen;
                    break;
            case 14: layoutId = R.layout.step_fifteen_screen;
                    break;
            case 15: layoutId = R.layout.step_sixteen_screen;
                    break;
            case 16: layoutId = R.layout.step_seventeen_screen;
                    break;
            case 17: layoutId = R.layout.step_eighteen_screen;
                    break;
            default: layoutId = R.layout.step_one_screen;
        }

        view = (MotionLayout) inflater.inflate(layoutId, container, false);
        return view;
    }

    public void setAnimationProgress(float progress) {
        // TODO: Set motion layout progress.
        if (view != null && page != 17) {
            view.setProgress(progress);
        }
    }



}