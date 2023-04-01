package ganabet.sporttime;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="my_db";
    public final static String TABLE_NAME="trainings";


    public final static String KEY_ID="_id";
    public final static String KEY_TRAINING_ID="training_id";
    public final static String KEY_NAME_EXERSISE="name_exersise";
    public final static String KEY_SETS="sets";
    public final static String KEY_REPS="reps";
    public final static String KEY_DATE="trining_date";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        //создаем таблицу
        db.execSQL("create table if not exists "+ TABLE_NAME + "("
                + KEY_ID+" integer primary key autoincrement, "+KEY_TRAINING_ID +" integer, "
                +KEY_NAME_EXERSISE+" text, "+KEY_SETS+" integer, "+KEY_REPS+" integer, "+KEY_DATE+" text"+")");

        Log.d("mLog","Created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
}
