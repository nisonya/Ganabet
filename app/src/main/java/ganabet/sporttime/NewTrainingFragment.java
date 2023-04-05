package ganabet.sporttime;

import static android.content.Context.MODE_PRIVATE;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NewTrainingFragment extends Fragment {

    private RelativeLayout.LayoutParams lp;
    boolean showBackLayout = false;
    private ConstraintLayout frontLayout;
    private ConstraintLayout backLayout;
    Button btnAddTrain, btnPickDate, backbtn;
    ImageView btnClose, btnAddExersise;
    TextView dateView, tvExercise;
    EditText edSets, edReps;
    DatePickerDialog.OnDateSetListener setListener;
    RecyclerView rvMucleAndExercise, rvAddedExercise;
    ArrayList<Muscles> muscles;
    ArrayList<Exercise> exersice;
    ArrayList<Exercise> addedExersise;
    List<String> muscle_name= Base.getName_muscle();
    List<String> exer_name= Base.getName_exer();
    List<Integer> muscle_pic= Base.getMuscleImage();
    List<Integer> muscle_id= Base.getMuscle_id();
    List<Integer> exerForMuscle= Base.getId_musle_for_ex();
    AddedExerciseAdapter addedExerciseAdapter;
    SharedPreferences sPrefID;
    SharedPreferences.Editor ed;
    private static final String FILE_NAME="ID_FILE_NAME";
    private static final String ID_TRAIN_STR="TRAINING_ID";


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

        addedExersise = new ArrayList<>();
        frontLayout = view.findViewById(R.id.front_layout);
        backLayout = view.findViewById(R.id.back_layout);
        dateView = view.findViewById(R.id.dateView);
        tvExercise = view.findViewById(R.id.tvExercise);
        backbtn = view.findViewById(R.id.back);
        btnAddExersise = view.findViewById(R.id.addExers);
        btnClose = view.findViewById(R.id.close);
        btnPickDate = view.findViewById(R.id.pickDateBtn);
        btnAddTrain = view.findViewById(R.id.addTrainBtn);
        //открытие шторки
        btnAddExersise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropLayout();
            }
        });
        //закрытие шторки
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
        //rv for added exercise
        rvAddedExercise = view.findViewById(R.id.addedExerciseRV);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvAddedExercise.setLayoutManager(layoutManager1);
        addedExerciseAdapter = new AddedExerciseAdapter(addedExersise,getActivity());

        //rv for exercise
        rvMucleAndExercise = view.findViewById(R.id.RVExerciseAndMuscles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMucleAndExercise.setLayoutManager(layoutManager);
        //add exercise data

        ExerciseForChoosenAdapter.OnExerciseClickListener onExerciseClickListener = new ExerciseForChoosenAdapter.OnExerciseClickListener() {
            @Override
            public void onExersiseClick( Exercise exercise) {
                boolean found=false;

                Log.d("exersise", String.valueOf(addedExersise.size()));
                if(addedExersise.size()==0){
                    addedExersise.add(exercise);
                    Log.d("exersise", "put");
                }
                else {
                    for (int i = 0; i < addedExersise.size(); i++) {
                        if (addedExersise.get(i).getName() == exercise.getName()) {
                            addedExersise.remove(i);
                            found = true;
                            Log.d("exersise", "deleted");
                        }
                    }
                    if (!found) {
                        addedExersise.add(exercise);
                        Log.d("exersise", "put");
                    }
                }
                Log.d("exersise", "clicked");
            }
        };

        //rv for muscles
        //add muscles data
        muscles = new ArrayList<>();
        for(int i = 0; i<12; i++){
            Muscles mMuscle = new Muscles(muscle_name.get(i),muscle_pic.get(i),muscle_id.get(i));
            muscles.add(mMuscle);
        }
        MusclesAdapter.OnMusclesClickListener onMusclesClickListener = new MusclesAdapter.OnMusclesClickListener() {
            @Override
            public void onMusclesClick( Muscles muscles) {
                backbtn.setVisibility(View.VISIBLE);
                tvExercise.setVisibility(View.VISIBLE);
                tvExercise.setText(muscles.getName());
                exersice = new ArrayList<>();
                for(int i = 0; i<41; i++) {
                    if (exerForMuscle.get(i) == muscles.getId()) {
                        Exercise mExercise = new Exercise(i, exer_name.get(i), muscles.getPic(),"0","0");
                        exersice.add(mExercise);
                    }
                }
                ExerciseForChoosenAdapter exerciseForChoosenAdapter = new ExerciseForChoosenAdapter(exersice,getActivity(), onExerciseClickListener);
                rvMucleAndExercise.setAdapter(exerciseForChoosenAdapter);
                Log.d("Muscle", "put");
            }
        };
        MusclesAdapter musclesAdapter = new MusclesAdapter(muscles,getActivity(), onMusclesClickListener);
        rvMucleAndExercise.setAdapter(musclesAdapter);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvExercise.setVisibility(View.INVISIBLE);
                backbtn.setVisibility(View.INVISIBLE);
                rvMucleAndExercise.setAdapter(musclesAdapter);
            }
        });

        //добавление тренировки
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        btnAddTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Exercise> finalExersise = addedExerciseAdapter.getData();

                int int_id = getSharedPrefID();
                System.out.println(getSharedPrefID());
                for(int i =0; i< finalExersise.size();i++){
                    Exercise exersiseItem = finalExersise.get(i);
                    cv.put(DBHelper.KEY_TRAINING_ID, int_id);
                    cv.put(DBHelper.KEY_NAME_EXERSISE, exersiseItem.getName());
                    cv.put(DBHelper.KEY_MUSCLE_PIC, exersiseItem.getPic());
                    cv.put(DBHelper.KEY_SETS, exersiseItem.getSets());
                    cv.put(DBHelper.KEY_REPS, exersiseItem.getReps());
                    cv.put(DBHelper.KEY_DATE, dateView.getText().toString());
                    database.insert(DBHelper.TABLE_NAME, null, cv);
                }
                saveToSP(int_id+1);
                addedExersise.clear();
                rvAddedExercise.setAdapter(addedExerciseAdapter);
                Toast.makeText(getActivity(),"Train added", Toast.LENGTH_LONG).show();
                System.out.println(getSharedPrefID());

            }
        });


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
            rvAddedExercise.setAdapter(addedExerciseAdapter);
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
    //получение id тренировки
    public int getSharedPrefID(){
        sPrefID = getActivity().getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        int train_id = sPrefID.getInt(ID_TRAIN_STR,1);
        return train_id;
    }
    //сохранение id локально
    public void saveToSP(int id){
        ed = sPrefID.edit();
        ed.putInt(ID_TRAIN_STR, id);
        ed.apply();
    }
}