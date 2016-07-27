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
        List<Todo> check = Todo.find(Todo.class, "done = 0 AND archived = 0");
        if (check != null && check.size() > 0) {
            todos = check;
        }
        return todos;
    }

    public List<String> pendingNames() {
        List<String> list = new ArrayList<>();
        List<Todo> check = pendings();
        if (check != null && check.size() > 0) {
            for (Todo todo : check) {
                list.add(todo.getName());
            }
        }
        return list;
    }

    public List<Todo> byName(String name) {
        List<Todo> todos = new ArrayList<>();
        String query = "name LIKE " + "'%"+name+"%'" + " AND done = 0 AND archived = 0";
        List<Todo> check = Todo.find(Todo.class, query);
        if (check != null && check.size() > 0) {
            todos = check;
        }
        return todos;
    }

}
