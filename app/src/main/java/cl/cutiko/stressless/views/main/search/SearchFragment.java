package cl.cutiko.stressless.views.main.search;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import cl.cutiko.stressless.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_search, container, false);

        ImageView expander = (ImageView) main.findViewById(R.id.searchIv);

        final AutoCompleteTextView autoComplete = (AutoCompleteTextView) main.findViewById(R.id.searchBar);
        autoComplete.setVisibility(View.GONE);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        autoComplete.animate().translationX(width-100).start();

        expander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoComplete.getVisibility() == View.GONE) {
                    autoComplete.setVisibility(View.VISIBLE);
                    autoComplete.animate().translationX(-width).setDuration(400).start();
                }
            }
        });


        return main;
    }

}
