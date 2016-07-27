package cl.cutiko.stressless.models;

import com.orm.SugarRecord;

/**
 * Created by cutiko on 27-07-16.
 */
public class ToDo extends SugarRecord {

    private String name;
    private boolean done;

    public ToDo() {
    }

    public ToDo(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
