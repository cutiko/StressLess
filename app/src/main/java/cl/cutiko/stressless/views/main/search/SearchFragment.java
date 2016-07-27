package cl.cutiko.stressless.views.main.search;


import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.stressless.R;
import cl.cutiko.stressless.data.Todos;
import cl.cutiko.stressless.views.main.SearchListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final int SEARCH_EXPANDED = 111;
    private static final int SEARCH_COLLAPSED = 000;

    private SearchListener listener;
    private List<String> names = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SearchListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_search, container, false);

        AutoCompleteTextView autoComplete = (AutoCompleteTextView) main.findViewById(R.id.searchBar);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;

        autoComplete.animate().translationX(width).setDuration(400).start();

        ImageView expander = (ImageView) main.findViewById(R.id.searchIv);
        expander.setTag(SEARCH_COLLAPSED);

        setSearchUi(autoComplete, expander, width);
        setCompletion(autoComplete);

        return main;
    }

    private void setSearchUi(final AutoCompleteTextView autoComplete, final ImageView expander, final int width){
        expander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SEARCH_COLLAPSED == expander.getTag()) {
                    autoComplete.animate().translationX(0).setDuration(400).start();
                    autoComplete.requestFocus();
                    expander.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_close_white_24dp));
                    expander.setTag(SEARCH_EXPANDED);
                    try {
                        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(autoComplete, InputMethodManager.SHOW_FORCED);
                    } catch (NullPointerException e) {

                    }
                } else {
                    autoComplete.animate().translationX(width).setDuration(400).start();
                    expander.setImageDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.ic_search_white_24dp));
                    expander.setTag(SEARCH_COLLAPSED);
                    autoComplete.setText("");
                    hideKeyboard();
                }
            }
        });
    }

    private void setCompletion(final AutoCompleteTextView autoTv){
        names = new Todos().pendingNames();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);
        autoTv.setAdapter(adapter);

        autoTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = autoTv.getText().toString();
                listener.searchBy(name);
                hideKeyboard();
            }
        });

        autoTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String name = autoTv.getText().toString();
                    if (name != null && !name.isEmpty() && !name.equals("") && name.trim().length() > 0) {
                        listener.searchBy(name);
                        hideKeyboard();
                        autoTv.dismissDropDown();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void add(String name) {
        names.add(name);
        adapter.notifyDataSetChanged();
    }

    private void hideKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }


}
