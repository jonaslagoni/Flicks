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
import jonaslagoni.fliks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseFragment extends Fragment {


    private RecyclerView recyclerView;

    private Context context;

    private MyAdapter recyclerView_Adapter;

    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private Flickr f;

    public BrowseFragment() {
        // Required empty public constructor
        f = new Flickr("b665313ceeefd9095f0f6bb6fcbefa57", "9ff48e279a496c8d");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        // Inflate the layout for this fragment
        final View _view = inflater.inflate(R.layout.fragment_browse, container, false);
        initRecycle(_view);
        final EditText e = ((EditText)_view.findViewById(R.id.browse_search_input));
        e.addTextChangedListener(new BrowseTextWatcher(this, recyclerView_Adapter, _view));
        e.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    PhotosInterface t = f.getPhotosInterface();
                    SearchParameters search_test = new SearchParameters();
                    search_test.setText(e.getText().toString());
                    recyclerView_Adapter.reset();
                    new BrowseController(_view).execute(new BrowsePara(f, t, search_test, recyclerView_Adapter, getActivity()));


                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    return true;
                }
                return false;
            }
        });
        setData(_view);
        return _view;
    }

    private void setData(View view){
        PhotosInterface t = f.getPhotosInterface();
        SearchParameters search_test = new SearchParameters();
        search_test.setText("Mountain");
        new BrowseController(view.findViewById(R.id.content_menu_drawer)).execute(new BrowsePara(f, t, search_test, recyclerView_Adapter, getActivity()));
    }

    private void initRecycle(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_component);
        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new MyAdapter(context);

        recyclerView.setAdapter(recyclerView_Adapter);
    }

}
