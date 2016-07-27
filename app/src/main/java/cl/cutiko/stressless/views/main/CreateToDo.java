package cl.cutiko.stressless.views.main;

import cl.cutiko.stressless.models.Todo;

/**
 * Created by cutiko on 27-07-16.
 */
public class CreateToDo {

    private CreateToDoCallback callback;

    public CreateToDo(CreateToDoCallback callback) {
        this.callback = callback;
    }

    public void validation(String name) {
        Todo todo = null;
        if (name != null && !name.isEmpty() && !name.equals("") && name.trim().length() > 0) {
            todo = new Todo(name);
            todo.save();
        }
        callback.result(todo);
    }
}
