package jonaslagoni.fliks.DataCom;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

/**
 * Created by jonas on 26-01-2017.
 */

public class BrowsePara {
    private Flickr flickr;
    private PhotosInterface photosInterface;
    private SearchParameters searchParameters;

    public BrowsePara(Flickr flickr, PhotosInterface photosInterface, SearchParameters searchParameters){
        this.flickr = flickr;
        this.photosInterface = photosInterface;
        this.searchParameters = searchParameters;
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
}
