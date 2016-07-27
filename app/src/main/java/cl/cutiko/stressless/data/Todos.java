package cl.cutiko.stressless.data;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.models.ToDo;

/**
 * Created by cutiko on 27-07-16.
 */
public class Todos {

    public List<ToDo> pendings() {
        List<ToDo> toDos = new ArrayList<>();
        List<ToDo> check = ToDo.find(ToDo.class, "done = 0");
        if (check != null && check.size() > 0) {
            toDos = check;
        }
        return toDos;
    }

}
