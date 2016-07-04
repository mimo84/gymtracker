package de.maurizionapoleoni.gymtraker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings: {
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
            }



        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
