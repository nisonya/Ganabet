package ganabet.sporttime;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class HistoryFragment extends Fragment {

    TextView dateView;
    Button btnPickDate;
    DatePickerDialog.OnDateSetListener setListener;
    RecyclerView rvHistory;
    AddedExerciseAdapter historyAdapter;
    List<Exercise> historyExercise = new ArrayList<>();
    SQLiteDatabase database;

    public HistoryFragment() {
        super(R.layout.fragment_history);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        dateView = view.findViewById(R.id.dateView);
        btnPickDate = view.findViewById(R.id.pickDateBtn);
        rvHistory = view.findViewById(R.id.hisroryRV);
        //дата
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
                setHistoryRV(selectedDay);

            }
        };
        DBHelper dbHelper = new DBHelper(getActivity());
        database = dbHelper.getWritableDatabase();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvHistory.setLayoutManager(layoutManager);
        historyAdapter = new AddedExerciseAdapter(historyExercise,getActivity(), true);
        setHistoryRV(now_date);

    }

    public void setHistoryRV(String date_str){
        getTrainForDate(date_str);
        rvHistory.setAdapter(historyAdapter);
    }

    public void getTrainForDate(String date_str){
        if(historyExercise!=null) historyExercise.clear();
        ContentValues cv = new ContentValues();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, "trining_date == \""+date_str+"\"", null, null, null,null);
        if(cursor.moveToFirst()){
            int idIndex =cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex =cursor.getColumnIndex(DBHelper.KEY_NAME_EXERSISE);
            int picIndex =cursor.getColumnIndex(DBHelper.KEY_MUSCLE_PIC);
            int dateIndex =cursor.getColumnIndex(DBHelper.KEY_DATE);
            int setsIndex =cursor.getColumnIndex(DBHelper.KEY_SETS);
            int repsIndex =cursor.getColumnIndex(DBHelper.KEY_REPS);
            do {
                Exercise mExersise = new Exercise(cursor.getInt(idIndex),cursor.getString(nameIndex),
                        cursor.getInt(picIndex), cursor.getString(setsIndex), cursor.getString(repsIndex));
                historyExercise.add(mExersise);
            }while(cursor.moveToNext());
        }
        else{
            Log.d("mLog","0 rows");
        }

        cursor.close();
    }
}