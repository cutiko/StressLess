package cl.cutiko.stressless.views.archive;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.cutiko.stressless.R;

public class ArchiveActivity extends AppCompatActivity implements ArchiveListener{

    private ActionBar actionBar;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        actionBar = getSupportActionBar();
    }

    @Override
    public void selectedItem(int counter) {
        this.counter += counter;
        actionBar.setTitle(getString(R.string.title_archive_selection, this.counter));
    }
}
