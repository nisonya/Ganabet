package ganabet.sporttime;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class StopwatchFragment extends Fragment {
    public EditText edMin, edSec;
    Button btn;
    public CountDownTimer myTimer;
    int strat_time = 10000;

    public StopwatchFragment() {
            super(R.layout.fragment_stopwatch);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_stopwatch);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.buttonStart);
        edMin = view.findViewById(R.id.editTextMinuts);
        edSec = view.findViewById(R.id.editTextSeconds);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timemin = edMin.getText().toString();
                String timesec = edSec.getText().toString();
                if((timesec.equals("")&&(timemin.equals("")))||(timesec.equals("0")&&(timemin.equals("0")))||(timesec.equals("")&&(timemin.equals("0")))
                ||(timesec.equals("0")&&(timemin.equals("")))){
                    Toast.makeText(getActivity(), "Put Value", Toast.LENGTH_SHORT).show();
                }
                else{
                    System.out.println("start");
                    Long seconds = Long.parseLong(timemin)*60+Long.parseLong(timesec);
                    timerCountDown(seconds);
                }
            }
        });
    }

    public void timerCountDown(Long time){
        myTimer = new CountDownTimer(time*1000, 1000) {
            @Override
            public void onTick(long l) {
                Long min = (l / 60000);
                Long sec = ((l % 60000) / 1000);
                edMin.setText(Long.toString(min));
                edSec.setText(Long.toString(sec));
                System.out.println("going");
            }

            @Override
            public void onFinish() {
                edMin.setText("0");
                edSec.setText("0");
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                // Передайте ссылку на разметку
                dialog.setContentView(R.layout.timer_out);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                TextView text_dialog = (TextView) dialog.findViewById(R.id.dialog_remainder);
                text_dialog.setText("Timer is out");
                Button btn_yes= (Button) dialog.findViewById(R.id.acc_yes);
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });

            }
        };
        myTimer.start();
    }

}