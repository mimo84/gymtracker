package de.maurizionapoleoni.gymtraker;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class AddExerciseDialog {

    public AddExerciseDialog () {

    }

    public void showDialog(Context c, View v, Effect<String> e) {


        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        // From here I am going to create a custom dialog.
        // I am not inside in the activity, need to get the inflater from the context
        // https://stackoverflow.com/questions/7803771/call-to-getlayoutinflater-in-places-not-in-activity
        LayoutInflater inflater = LayoutInflater.from(c);
        View dialogView = inflater.inflate(R.layout.add_exercise, null);
        builder.setView(dialogView);

        builder.setTitle("Add Exercise");
        builder.setCancelable(true);

        builder.setPositiveButton("Add", (dialog, which) -> {
            EditText newExerciseName = (EditText) dialogView.findViewById(R.id.newExerciseName);
            e.effect(newExerciseName.getText().toString());
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
