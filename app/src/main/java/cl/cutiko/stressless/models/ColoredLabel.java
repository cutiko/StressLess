package cl.cutiko.stressless.models;

import com.orm.SugarRecord;

/**
 * Created by cutiko on 28-07-16.
 */
public class ColoredLabel extends SugarRecord{

    private String name, color;

    public ColoredLabel() {
    }

    public ColoredLabel(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
