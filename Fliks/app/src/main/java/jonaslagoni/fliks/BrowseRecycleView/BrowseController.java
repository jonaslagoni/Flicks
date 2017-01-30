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

import jonaslagoni.fliks.R;

/**
 * Created by jonas on 26-01-2017.
 */

public class BrowseController extends AsyncTask<Object, Void, PhotoList> {
    private View rootView;

    /**
     * SetS the rootView
     * @param rootView View
     */
    public BrowseController(View rootView){
        this.rootView = rootView;
    }

    /**
     * Retreives the Photolist from flickr
     * @param params Object
     * @return
     */
    @Override
    protected PhotoList doInBackground(Object... params) {
        PhotoList photolist_test = null;
        //get the parameters
        final BrowsePara browsePara = (BrowsePara)params[0];
        // Enable the spinner
        browsePara.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rootView.findViewById(R.id.fragmentBrowse_loading).setVisibility(View.VISIBLE);
            }
        });
        //try get the photolist from flickr
        try {
            photolist_test = browsePara.getFlickr().getPhotosInterface().search(browsePara.getSearchParameters(), 200, 1);
            //add each of the photos the recyclerview
            for(final Photo p: photolist_test){
                browsePara.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        browsePara.getMyAdapter().addPhoto(p);
                    }
                });
            }
        }catch(IOException e){
            Snackbar.make(rootView, "Check your internet connection #3000", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }catch(FlickrException e){
            Snackbar.make(rootView, "Make sure you search for something", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }catch(JSONException e){
            Snackbar.make(rootView, "Internal error #3002", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        // hide the spinner when done
        browsePara.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rootView.findViewById(R.id.fragmentBrowse_loading).setVisibility(View.GONE);
            }
        });
        return photolist_test;
    }

    @Override
    protected void onPostExecute(PhotoList result) {}
}
