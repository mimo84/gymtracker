package de.maurizionapoleoni.gymtraker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExerciseDataSource exerciseDataSource;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.exerciseList);

        exerciseDataSource = new ExerciseDataSource(this);
        exerciseDataSource.open();

        List<Exercise> values = exerciseDataSource.getAllExercises();

        ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        ArrayAdapter<Exercise> adapter = (ArrayAdapter<Exercise>) listView.getAdapter();
        switch (view.getId()) {
            case R.id.addExercise:

                AddExerciseDialog exerciseDialog = new AddExerciseDialog();
                // How do you use a lambda expression here?
                exerciseDialog.showDialog(this, view, (newExer) -> {
                     Log.d("LAMBDA", newExer);

//                    String[] exercises = new String[] { "Cool", "Very nice", "Hate it" };
//                    int nextInt = new Random().nextInt(3);
//                    // save the new exercise to the database
                    Exercise exercise = exerciseDataSource.createExercise(newExer);
                    adapter.add(exercise);
                });
                break;
            case R.id.deleteExercise:
                if (listView.getAdapter().getCount() > 0) {
                    Exercise exercise = null;
                    exercise = (Exercise) listView.getAdapter().getItem(0);
                    exerciseDataSource.deleteExercise(exercise);
                    adapter.remove(exercise);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }



}
