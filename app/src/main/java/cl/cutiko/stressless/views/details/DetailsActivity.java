package cl.cutiko.stressless.views.details;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.models.Pending;
import cl.cutiko.stressless.views.main.todoList.TodoListFragment;

public class DetailsActivity extends AppCompatActivity {

    private Pending pending;
    private EditText descriptionEt;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        pending = Pending.findById(Pending.class, getIntent().getLongExtra(TodoListFragment.TODO_ID, 0));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(pending.getName());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        descriptionEt = (EditText) findViewById(R.id.descriptionEt);
        setDescription();

        spinner = (Spinner) findViewById(R.id.categoryDd);
    }

    private void setDescription(){
        String description = (pending.getDescription() != null) ? pending.getDescription() : "";
        descriptionEt.setText(description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveDescription();
    }

    private void saveDescription(){
        String description  = descriptionEt.getText().toString();
        if (description != null && !description.isEmpty() && !description.equals("") && description.trim().length() > 0) {
            pending.setDescription(description);
            pending.save();
        }
    }
}
