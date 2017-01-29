package jonaslagoni.fliks.BrowseRecycleView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.Fragments.BrowseFragment;
import jonaslagoni.fliks.R;

/**
 * Created by jonas on 27-01-2017.
 */

public class BrowseTextWatcher implements TextWatcher {
    private BrowseFragment browseFragment;
    private MyAdapter recyclerView_Adapter;
    private View _view;
    public BrowseTextWatcher(BrowseFragment browseFragment, MyAdapter recyclerView_Adapter, View _view){
        this.browseFragment = browseFragment;
        this.recyclerView_Adapter = recyclerView_Adapter;
        this._view = _view;
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() > 3) {
            Flickr f = new Flickr("b665313ceeefd9095f0f6bb6fcbefa57");
            PhotosInterface t = f.getPhotosInterface();
            SearchParameters search_test = new SearchParameters();
            search_test.setText(s.toString());
            recyclerView_Adapter.reset();
            new BrowseController(_view).execute(new BrowsePara(f, t, search_test, recyclerView_Adapter, browseFragment.getActivity()));
        }
    }
}
