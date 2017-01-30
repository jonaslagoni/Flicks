package jonaslagoni.fliks.BrowseRecycleView;

import com.googlecode.flickrjandroid.photos.Photo;

/**
 * Created by jonas on 27-01-2017.
 */

public class PhotoList {
    private String thumnailUrl;
    private String imageUrl;

    /**
     * Containing all the relevant information about this photo
     * @param photo
     */
    public PhotoList(Photo photo){
        thumnailUrl = photo.getThumbnailUrl();
        imageUrl = photo.getLargeUrl();
    }

    /**
     * Url for the thumbnail picture
     * @return String
     */
    public String getThumnailUrl() {
        return thumnailUrl;
    }

    /**
     * Url for the full picture, if the user wants to view it in fullscreen
     * @return String
     */
    public String getImageUrl() {
        return imageUrl;
    }

}
