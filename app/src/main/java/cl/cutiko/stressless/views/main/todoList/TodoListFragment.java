package cl.cutiko.stressless.views.main.todoList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.adapters.TodoAdapter;
import cl.cutiko.stressless.models.Pending;
import cl.cutiko.stressless.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListFragment extends Fragment implements ClickListener {

    private TodoAdapter adapter;

    public static final int DETAILS_INTENT = 333;
    public static final String TODO_ID = "TODO_ID";
    public static final String TODO_POSITION = "TODO_POSITION";

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

        adapter = new TodoAdapter(this);

        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.reset();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                }, 1200);
            }
        });

        return mainView;
    }

    @Override
    public void itemClicked(Long id, int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(TODO_ID, id);
        intent.putExtra(TODO_POSITION, position);
        startActivityForResult(intent, DETAILS_INTENT);
    }

    public void refresh() {
        adapter.reset();
    }

    public void add(Pending pending) {
        adapter.add(pending);
    }

    public void search(String name) {
        adapter.byName(name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILS_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                int position = data.getIntExtra(TODO_POSITION, 0);
                long id = data.getLongExtra(TODO_ID, 0);
                adapter.update(position, id);
            } else {
                int position = data.getIntExtra(TODO_POSITION, 0);
                adapter.delete(position);
            }
        }
    }
}
