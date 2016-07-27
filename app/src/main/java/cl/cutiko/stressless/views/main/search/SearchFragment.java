package cl.cutiko.stressless.views.main.search;


import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import cl.cutiko.stressless.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final int SEARCH_EXPANDED = 111;
    private static final int SEARCH_COLLAPSED = 000;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_search, container, false);

        final AutoCompleteTextView autoComplete = (AutoCompleteTextView) main.findViewById(R.id.searchBar);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;

        autoComplete.animate().translationX(width).start();

        final ImageView expander = (ImageView) main.findViewById(R.id.searchIv);
        expander.setTag(SEARCH_COLLAPSED);

        final InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        expander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SEARCH_COLLAPSED == expander.getTag()) {
                    autoComplete.animate().translationX(0).setDuration(400).start();
                    autoComplete.requestFocus();
                    expander.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_close_white_24dp));
                    expander.setTag(SEARCH_EXPANDED);
                    try {
                        inputMethodManager.showSoftInput(autoComplete, InputMethodManager.SHOW_FORCED);
                    } catch (NullPointerException e) {

                    }
                } else {
                    autoComplete.animate().translationX(width).setDuration(400).start();
                    expander.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_search_white_24dp));
                    expander.setTag(SEARCH_COLLAPSED);
                    autoComplete.setText("");
                    try {
                        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                    } catch (Exception e) {

                    }
                }
            }
        });

        return main;
    }

}
