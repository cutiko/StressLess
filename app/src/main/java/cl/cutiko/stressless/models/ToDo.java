package cl.cutiko.stressless.models;

import com.orm.SugarRecord;

/**
 * Created by cutiko on 27-07-16.
 */
public class Todo extends SugarRecord {

    private String name, description;
    private boolean done;

    public Todo() {
    }

    public Todo(String name) {
        this.name = name;
        done = false;
        description = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void undo() {
        done = false;
        save();
    }
}
