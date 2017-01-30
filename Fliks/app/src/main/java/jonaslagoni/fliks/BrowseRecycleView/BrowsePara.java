package jonaslagoni.fliks.BrowseRecycleView;

import android.app.Activity;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.Fragments.BrowseFragment;

/**
 * Created by jonas on 26-01-2017.
 */

public class BrowsePara {
    private Flickr flickr;
    private SearchParameters searchParameters;
    private MyAdapter myAdapter;
    private Activity activity;

    /**
     * This is used to store the information needed for the arguments for the AsyncTask browsecontroller
     * @param flickr Flickr
     * @param searchParameters SearchParameters
     * @param myAdapter MyAdapter
     * @param activity Activity
     */
    public BrowsePara(Flickr flickr, SearchParameters searchParameters, MyAdapter myAdapter, Activity activity){
        this.flickr = flickr;
        this.searchParameters = searchParameters;
        this.myAdapter = myAdapter;
        this.activity = activity;
    }

    /**
     * @return flickr
     */
    public Flickr getFlickr() {
        return flickr;
    }

    /**
     * @return SearchParameters
     */
    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    /**
     * @return MyAdapter
     */
    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    /**
     * @return Activity
     */
    public Activity getActivity() { return activity; }
}
