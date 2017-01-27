package jonaslagoni.fliks.BrowseRecycleView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.BrowsePictures;

/**
 * Created by jonas on 26-01-2017.
 */

public class BrowsePara {
    private Flickr flickr;
    private PhotosInterface photosInterface;
    private SearchParameters searchParameters;
    private MyAdapter myAdapter;
    private BrowsePictures browsePictures;
    public BrowsePara(Flickr flickr, PhotosInterface photosInterface, SearchParameters searchParameters, MyAdapter myAdapter, BrowsePictures browsePictures){
        this.flickr = flickr;
        this.photosInterface = photosInterface;
        this.searchParameters = searchParameters;
        this.myAdapter = myAdapter;
        this.browsePictures = browsePictures;
    }

    public Flickr getFlickr() {
        return flickr;
    }

    public void setFlickr(Flickr flickr) {
        this.flickr = flickr;
    }

    public PhotosInterface getPhotosInterface() {
        return photosInterface;
    }

    public void setPhotosInterface(PhotosInterface photosInterface) {
        this.photosInterface = photosInterface;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }


    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    public void setMyAdapter(MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }


    public BrowsePictures getBrowsePictures() {
        return browsePictures;
    }

    public void setBrowsePictures(BrowsePictures browsePictures) {
        this.browsePictures = browsePictures;
    }


}
