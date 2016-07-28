package cl.cutiko.stressless.views.main;

import cl.cutiko.stressless.models.Pending;

/**
 * Created by cutiko on 27-07-16.
 */
public class CreateToDo {

    private CreateToDoCallback callback;

    public CreateToDo(CreateToDoCallback callback) {
        this.callback = callback;
    }

    public void validation(String name) {
        Pending pending = null;
        if (name != null && !name.isEmpty() && !name.equals("") && name.trim().length() > 0) {
            pending = new Pending(name);
            pending.save();
        }
        callback.result(pending);
    }
}
