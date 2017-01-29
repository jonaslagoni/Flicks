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
    private PhotosInterface photosInterface;
    private SearchParameters searchParameters;
    private MyAdapter myAdapter;
    private Activity activity;
    public BrowsePara(Flickr flickr, PhotosInterface photosInterface, SearchParameters searchParameters, MyAdapter myAdapter, Activity activity){
        this.flickr = flickr;
        this.photosInterface = photosInterface;
        this.searchParameters = searchParameters;
        this.myAdapter = myAdapter;
        this.activity = activity;
    }

    public Flickr getFlickr() {
        return flickr;
    }
    public PhotosInterface getPhotosInterface() {
        return photosInterface;
    }
    public SearchParameters getSearchParameters() {
        return searchParameters;
    }
    public MyAdapter getMyAdapter() {
        return myAdapter;
    }
    public Activity getActivity() { return activity; }
}
