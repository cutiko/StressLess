package cl.cutiko.stressless.data;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.models.Pending;

/**
 * Created by cutiko on 27-07-16.
 */
public class Todos {

    public List<Pending> pendings() {
        List<Pending> pendings = new ArrayList<>();
        List<Pending> check = Pending.find(Pending.class, "done = 0");
        if (check != null && check.size() > 0) {
            pendings = check;
        }
        return pendings;
    }

    public List<String> pendingNames() {
        List<String> list = new ArrayList<>();
        List<Pending> check = pendings();
        if (check != null && check.size() > 0) {
            for (Pending pending : check) {
                list.add(pending.getName());
            }
        }
        return list;
    }

    public List<Pending> byName(String name) {
        List<Pending> pendings = new ArrayList<>();
        String query = "name LIKE " + "'%"+name+"%'" + " AND done = 0";
        List<Pending> check = Pending.find(Pending.class, query);
        if (check != null && check.size() > 0) {
            pendings = check;
        }
        return pendings;
    }

    public List<Pending> archived() {
        List<Pending> pendings = new ArrayList<>();
        List<Pending> check = Pending.find(Pending.class, "done = 1");
        if (check != null && check.size() > 0) {
            pendings = check;
        }
        return pendings;
    }

}
