package cl.cutiko.stressless.views.archive;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.adapters.ArchiveAdapter;
import cl.cutiko.stressless.data.Todos;

public class ArchiveActivity extends AppCompatActivity implements ArchiveListener{

    private ActionBar actionBar;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        actionBar = getSupportActionBar();
        try {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {

        }

        setAdapter();
    }

    private void setAdapter(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.archiveList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(layoutManager);

        ArchiveAdapter adapter = new ArchiveAdapter(new Todos().archived(), this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void selectedItem(int counter) {
        this.counter += counter;
        actionBar.setTitle(getString(R.string.title_archive_selection, this.counter));
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
