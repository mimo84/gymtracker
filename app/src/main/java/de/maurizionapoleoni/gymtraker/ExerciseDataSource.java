package de.maurizionapoleoni.gymtraker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ExerciseDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
        MySQLiteHelper.COLUMN_ID,
        MySQLiteHelper.COLUMN_EXCERCISE
    };

    public ExerciseDataSource(Context c) {
        dbHelper = new MySQLiteHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise (String exercise) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_EXCERCISE, exercise);

        long insertId = database.insert(MySQLiteHelper.TABLE_EXCERCISES, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXCERCISES, allColumns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();

        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise exercise) {
        long id = exercise.getId();
        Log.d("ExerciseDataSource", "Delete exercise with ID: " + id);
        database.delete(MySQLiteHelper.TABLE_EXCERCISES, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_EXCERCISES, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            exercises.add(exercise);
            cursor.moveToNext();
        }

        cursor.close();
        return exercises;
    }

    private Exercise cursorToExercise(Cursor cursor) {
        Exercise exercise = new Exercise();
        exercise.setId(cursor.getLong(0));
        exercise.setExercise(cursor.getString(1));
        return exercise;
    }



}
