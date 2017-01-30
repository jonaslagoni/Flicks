package jonaslagoni.fliks.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.BrowseRecycleView.BrowseController;
import jonaslagoni.fliks.BrowseRecycleView.BrowsePara;
import jonaslagoni.fliks.BrowseRecycleView.BrowseTextWatcher;
import jonaslagoni.fliks.BrowseRecycleView.MyAdapter;
import jonaslagoni.fliks.MenuDrawer;
import jonaslagoni.fliks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context;
    private MyAdapter recyclerView_Adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private Flickr flickr;

    public BrowseFragment(){
        // Required empty public constructor
    }

    /**
     * When this view is created do something
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get the flickr from activity
        flickr = ((MenuDrawer)getActivity()).getFlickr();
        context = container.getContext();
        // Inflate the layout for this fragment
        final View _view = inflater.inflate(R.layout.fragment_browse, container, false);
        // Init the recyclerview
        initRecycle(_view);

        //get the EditText for search
        final EditText e = ((EditText)_view.findViewById(R.id.browse_search_input));
        // Add custom TextWatcher to listen for whenever the user writes
        e.addTextChangedListener(new BrowseTextWatcher(this, recyclerView_Adapter, _view, flickr));
        // Set a key listener for when the user wants results
        e.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Create new search parameters and add the user input
                    SearchParameters search_test = new SearchParameters();
                    search_test.setText(e.getText().toString());

                    //clear all the current listed pictures from the recyclerview adapter
                    recyclerView_Adapter.reset();

                    //create a new AsyncTask to process the request
                    new BrowseController(_view).execute(new BrowsePara(flickr, search_test, recyclerView_Adapter, getActivity()));

                    //toggle the keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    return true;
                }
                return false;
            }
        });
        // Set some start data for show
        setData(_view);
        return _view;
    }

    /**
     * Sets some start pictures from the text 'Mountain'
     * @param view View
     */
    private void setData(View view){
        SearchParameters search_test = new SearchParameters();
        search_test.setText("Mountain");
        new BrowseController(view).execute(new BrowsePara(flickr, search_test, recyclerView_Adapter, getActivity()));
    }

    /**
     * Initializes the recyclerview
     * @param view View
     */
    private void initRecycle(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_component);
        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new MyAdapter(context);

        recyclerView.setAdapter(recyclerView_Adapter);
    }

}
