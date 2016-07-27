package cl.cutiko.stressless.views.main;

import android.widget.EditText;

import cl.cutiko.stressless.models.ToDo;

/**
 * Created by cutiko on 27-07-16.
 */
public class CreateToDo {

    private CreateToDoCallback callback;

    public CreateToDo(CreateToDoCallback callback) {
        this.callback = callback;
    }

    public void validation(String name) {
        if (name != null && !name.isEmpty() && !name.equals("") && name.trim().length() > 0) {
            new ToDo(name).save();
        }
        callback.result();
    }
}
