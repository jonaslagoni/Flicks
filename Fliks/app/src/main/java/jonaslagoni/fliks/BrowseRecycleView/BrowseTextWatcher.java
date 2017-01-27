package jonaslagoni.fliks.BrowseRecycleView;

import android.text.Editable;
import android.text.TextWatcher;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.BrowsePictures;
import jonaslagoni.fliks.R;

/**
 * Created by jonas on 27-01-2017.
 */

public class BrowseTextWatcher implements TextWatcher {
    private BrowsePictures browsePictures;
    private MyAdapter recyclerView_Adapter;
    public BrowseTextWatcher(BrowsePictures browsePictures, MyAdapter recyclerView_Adapter){
        this.browsePictures = browsePictures;
        this.recyclerView_Adapter = recyclerView_Adapter;
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
            new BrowseController(browsePictures.findViewById(R.id.content_menu_drawer)).execute(new BrowsePara(f, t, search_test, recyclerView_Adapter, browsePictures));
        }
    }
}
