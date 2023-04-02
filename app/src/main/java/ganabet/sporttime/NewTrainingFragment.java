package ganabet.sporttime;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewTrainingFragment extends Fragment {

    private RelativeLayout.LayoutParams lp;
    boolean showBackLayout = false;
    private ConstraintLayout frontLayout;
    private ConstraintLayout backLayout;
    Button btnAddTrain, btnPickDate;
    ImageView btnClose, btnAddExersise;
    TextView dateView;
    DatePickerDialog.OnDateSetListener setListener;

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
        dateView = view.findViewById(R.id.dateView);
        btnAddExersise = view.findViewById(R.id.addExers);
        btnClose = view.findViewById(R.id.close);
        btnPickDate = view.findViewById(R.id.pickDateBtn);
        btnAddTrain = view.findViewById(R.id.addTrainBtn);
        btnAddExersise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropLayout();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropLayout();
            }
        });
        String months[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12"};
        String now_date;
        GregorianCalendar gcalendar = new GregorianCalendar();
        if(String.valueOf(gcalendar.get(Calendar.DATE)).length()==1) now_date = months[gcalendar.get(Calendar.MONTH)] + ".0" + gcalendar.get(Calendar.DATE) + "." + gcalendar.get(Calendar.YEAR);
        else  now_date = months[gcalendar.get(Calendar.MONTH)] + ".0" + gcalendar.get(Calendar.DATE) + "." + gcalendar.get(Calendar.YEAR);
        dateView.setText(now_date);
        btnAddTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Calendar calendar = Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_Material_Dialog, setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String selectedDay;
                if(String.valueOf(i2).length()==1) selectedDay = months[i1] + ".0" + i2 + "." + i;
                else  selectedDay = months[i1] + "." + i2 + "." + i;
                dateView.setText(selectedDay);

            }
        };


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