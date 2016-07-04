package de.maurizionapoleoni.gymtraker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by maurizionapoleoni on 4/07/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_EXCERCISES = "exercises";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXCERCISE = "exercise";

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_EXCERCISES + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_EXCERCISE
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXCERCISES);
        onCreate(db);
    }


}
