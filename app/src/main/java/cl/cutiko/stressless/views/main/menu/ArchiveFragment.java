package cl.cutiko.stressless.views.main.menu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.views.archive.ArchiveActivity;
import cl.cutiko.stressless.views.main.ArchiveListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {

    private static final int ARCHIVE_INTENT = 222;
    private ArchiveListener listener;


    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ArchiveListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_archive, container, false);

        ImageButton archive = (ImageButton) main.findViewById(R.id.archivesBtn);
        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ArchiveActivity.class);
                startActivityForResult(intent, ARCHIVE_INTENT);
            }
        });


        return main;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ARCHIVE_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                listener.unarchive();
            }
        }
    }

}
