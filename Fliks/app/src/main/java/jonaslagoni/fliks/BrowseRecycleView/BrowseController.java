package jonaslagoni.fliks.BrowseRecycleView;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jonaslagoni.fliks.MySingleton;
import jonaslagoni.fliks.R;

/**
 * Created by jonas on 26-01-2017.
 */

public class BrowseController extends AsyncTask<Object, Void, PhotoList> {
    private View rootView;
    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    public BrowseController(View rootView){
        this.rootView = rootView;
    }
    @Override
    protected PhotoList doInBackground(Object... params) {
        final BrowsePara browsePara = (BrowsePara)params[0];
        try {
            PhotoList photolist_test = browsePara.getPhotosInterface().search(browsePara.getSearchParameters(), 200, 1);
            for(final Photo p: photolist_test){
                browsePara.getBrowsePictures().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        browsePara.getMyAdapter().addPhoto(p);
                    }
                });
            }
            return photolist_test;
        }catch(IOException e){
            Snackbar.make(rootView, "Check your internet connection #3000", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }catch(FlickrException e){
            Snackbar.make(rootView, "Internal error #3001", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }catch(JSONException e){
            Snackbar.make(rootView, "Internal error #3002", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        return null;
    }
    @Override
    protected void onPostExecute(PhotoList result) {}
}
