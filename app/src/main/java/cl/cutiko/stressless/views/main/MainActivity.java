package cl.cutiko.stressless.views.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.models.Todo;
import cl.cutiko.stressless.views.main.menu.SearchFragment;
import cl.cutiko.stressless.views.main.todoList.TodoListFragment;

public class MainActivity extends AppCompatActivity implements CreateToDoCallback, SearchListener, ArchiveListener {

    private Dialog dialog;
    private TodoListFragment listFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listFragment = (TodoListFragment) getSupportFragmentManager().findFragmentById(R.id.todoListFragment);
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.searchFragment);
        setFab();
    }

    private void setFab() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_create_todo);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final EditText inputEt = (EditText) dialog.findViewById(R.id.todoInput);
        ImageButton saveBtn = (ImageButton) dialog.findViewById(R.id.saveBtn);

        final CreateToDo createToDo = new CreateToDo(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputEt.getText().toString();
                createToDo.validation(name);
            }
        });

        inputEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                    String name = inputEt.getText().toString();
                    createToDo.validation(name);
                    return true;
                }
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEt.setText("");
                dialog.show();
            }
        });
    }

    @Override
    public void result(Todo todo) {
        if (todo != null) {
            listFragment.add(todo);
            searchFragment.add(todo.getName());
        }
        dialog.dismiss();
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        } catch (Exception e) {

        }

    }

    @Override
    public void searchBy(String name) {
        listFragment.search(name);
    }

    @Override
    public void unarchive() {
        listFragment.refresh();
    }
}
