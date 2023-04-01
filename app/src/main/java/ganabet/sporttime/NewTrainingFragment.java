package ganabet.sporttime;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NewTrainingFragment extends Fragment {

    private RelativeLayout.LayoutParams lp;
    boolean showBackLayout = false;
    private ConstraintLayout frontLayout;
    private ConstraintLayout backLayout;

    public NewTrainingFragment() {
        super(R.layout.fragment_new_training);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frontLayout = view.findViewById(R.id.front_layout);
        backLayout = view.findViewById(R.id.back_layout);

    }
    private void dropLayout(){
        showBackLayout=!showBackLayout;
        lp = (RelativeLayout.LayoutParams) frontLayout.getLayoutParams();
        if(showBackLayout){
            ValueAnimator var = ValueAnimator.ofInt(backLayout.getHeight());
            var.setDuration(300);
            var.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    lp.setMargins(0, (Integer) animation.getAnimatedValue(),0,0);
                    frontLayout.setLayoutParams(lp);

                }
            });

            var.start();
        }
        else {
            lp.setMargins(0,0,0,0);
            frontLayout.setLayoutParams(lp);
            TranslateAnimation animation = new TranslateAnimation(0,0,backLayout.getHeight(),0);
            animation.setStartOffset(0);
            animation.setDuration(200);
            frontLayout.setAnimation(animation);
        }
    }
}