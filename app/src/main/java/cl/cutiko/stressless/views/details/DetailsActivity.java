package cl.cutiko.stressless.views.details;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.adapters.LabelsAdapter;
import cl.cutiko.stressless.models.ColoredLabel;
import cl.cutiko.stressless.models.Pending;
import cl.cutiko.stressless.views.main.todoList.TodoListFragment;

public class DetailsActivity extends AppCompatActivity {

    private Pending pending;
    private EditText descriptionEt;
    private Spinner spinner;
    private Dialog dialog;

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

        setDialog();

        spinner = (Spinner) findViewById(R.id.categoryDd);
        setSpinner();
    }

    private void setSpinner() {
        ColoredLabel prompt = new ColoredLabel(getString(R.string.category_hint), "FF4081");
        List<ColoredLabel> labels = new ArrayList<>();
        labels.add(prompt);
        LabelsAdapter adapter = new LabelsAdapter(this, R.layout.list_item_colored_label, labels);
        spinner.setAdapter(adapter);
    }

    private void setDialog() {
        dialog = new Dialog(this, R.style.FullscreenTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_create_label);

        ImageView closeBtn = (ImageView) dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        final EditText labelInput = (EditText) dialog.findViewById(R.id.labelName);

        SpectrumPalette spectrumPalette = (SpectrumPalette) dialog.findViewById(R.id.colorPicker);
        int[] colors = getResources().getIntArray(R.array.demo_colors);
        spectrumPalette.setColors(colors);
        spectrumPalette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
            @Override
            public void onColorSelected(@ColorInt int color) {
                String name = labelInput.getText().toString();
                if (name != null && !name.isEmpty() && !name.equals("") && name.trim().length() > 0) {
                    ColoredLabel label = new ColoredLabel(name, Integer.toHexString(color).toUpperCase());
                    label.save();
                    pending.setCategory_id(label.getId());
                    pending.save();
                    dialog.dismiss();
                } else {
                    labelInput.setError(getString(R.string.label_error));
                }
            }
        });
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
        } else if (item.getItemId() == R.id.actionsLabel) {
            dialog.show();
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
