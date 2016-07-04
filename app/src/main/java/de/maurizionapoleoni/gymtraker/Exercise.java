package de.maurizionapoleoni.gymtraker;


public class Exercise {

    private long id;
    private String exercise;

    public long getId() {
        return id;
    }

    public void setId(long id) {

    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String excersise) {
        this.exercise = excersise;
    }

    @Override
    public String toString() {
        return exercise;
    }
}
