package cl.cutiko.stressless.data;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.models.Todo;

/**
 * Created by cutiko on 27-07-16.
 */
public class Todos {

    public List<Todo> pendings() {
        List<Todo> todos = new ArrayList<>();
        List<Todo> check = Todo.find(Todo.class, "done = 0");
        if (check != null && check.size() > 0) {
            todos = check;
        }
        return todos;
    }

}
