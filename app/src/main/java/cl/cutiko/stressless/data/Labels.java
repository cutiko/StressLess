package cl.cutiko.stressless.data;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.models.ColoredLabel;

/**
 * Created by cutiko on 28-07-16.
 */
public class Labels {

    public List<ColoredLabel> all() {
        List<ColoredLabel> list = new ArrayList<>();
        List<ColoredLabel> check = ColoredLabel.listAll(ColoredLabel.class);
        if (check != null && check.size() > 0) {
            list = check;
        }
        return list;
    }
}
