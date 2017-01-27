package jonaslagoni.fliks.BrowseRecycleView;

import com.googlecode.flickrjandroid.photos.Photo;

/**
 * Created by jonas on 27-01-2017.
 */

public class PhotoList {
    private String thumnailUrl;
    private String imageUrl;
    public PhotoList(Photo photo){
        thumnailUrl = photo.getThumbnailUrl();
        imageUrl = photo.getLargeUrl();
    }
    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
