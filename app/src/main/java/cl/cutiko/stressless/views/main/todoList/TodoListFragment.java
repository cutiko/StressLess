package cl.cutiko.stressless.views.main.todoList;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.adapters.TodoAdapter;
import cl.cutiko.stressless.data.Todos;
import cl.cutiko.stressless.models.Todo;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListFragment extends Fragment implements ClickListener {

    private TodoAdapter adapter;

    public TodoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.todoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new TodoAdapter(new Todos().pendings(), this);

        recyclerView.setAdapter(adapter);

        return mainView;
    }

    @Override
    public void itemClicked(Long id, int position) {

    }

    public void add(Todo todo) {
        adapter.add(todo);
    }

    public void search(String name) {
        adapter.byName(name);
    }
}
