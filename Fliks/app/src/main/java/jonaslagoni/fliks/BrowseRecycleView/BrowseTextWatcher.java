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
    private Flickr flickr;

    /**
     * Watches for text change
     * @param browseFragment BrowseFragment
     * @param recyclerView_Adapter MyAdapter
     * @param _view View
     * @param flickr Flickr
     */
    public BrowseTextWatcher(BrowseFragment browseFragment, MyAdapter recyclerView_Adapter, View _view, Flickr flickr){
        this.browseFragment = browseFragment;
        this.recyclerView_Adapter = recyclerView_Adapter;
        this._view = _view;
        this.flickr = flickr;
    }

    /**
     * After text change is done
     * @param s Editable
     */
    @Override
    public void afterTextChanged(Editable s) {}

    /**
     * @param s CharSequence
     * @param start int
     * @param count int
     * @param after int
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    /**
     * When the text change change the list of pictures
     * @param s CharSequence
     * @param start int
     * @param before int
     * @param count int
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //if the user haven't entered more then 3 letters don't do anything
        if(s.length() > 3) {
            //create a new search for pictures
            SearchParameters search_test = new SearchParameters();
            search_test.setText(s.toString());
            //clear the recyclerview adapter
            recyclerView_Adapter.reset();
            //find the new pictures
            new BrowseController(_view).execute(new BrowsePara(flickr, search_test, recyclerView_Adapter, browseFragment.getActivity()));
        }
    }
}
