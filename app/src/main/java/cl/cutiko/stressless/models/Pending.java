package cl.cutiko.stressless.models;

import com.orm.SugarRecord;

/**
 * Created by cutiko on 27-07-16.
 */
public class Pending extends SugarRecord {

    private String name, description;
    private boolean done;
    private long category_id;

    public Pending() {
    }

    public Pending(String name) {
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

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public void undo() {
        done = false;
        save();
    }
}
