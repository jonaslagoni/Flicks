package jonaslagoni.fliks.DataCom;

import android.os.AsyncTask;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.photos.PhotoList;

import org.json.JSONException;

import java.io.IOException;

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
        try {
            BrowsePara browsePara = (BrowsePara)params[0];
            PhotoList photolist_test = browsePara.getPhotosInterface().search(browsePara.getSearchParameters(), 1, 1);
            return photolist_test;
        }catch(IOException e){
            System.out.println(e);
        }catch(FlickrException e){
            System.out.println(e);
        }catch(JSONException e){
            System.out.println(e);
        }
        return null;
    }
    @Override
    protected void onPostExecute(PhotoList result) {
        if(result != null){
            super.onPostExecute(result);
            // Do things like hide the progress bar or change a TextView
            System.out.println(result.get(0).getTitle());
            //TextView t = (TextView)rootView.findViewById(R.id.textView2);
            //t.setText(result.get(0).getUrl());
            // Get the NetworkImageView that will display the image.
            mNetworkImageView = (NetworkImageView) rootView.findViewById(R.id.ThumbnailNetworkView);

            // Get the ImageLoader through your singleton class.
            mImageLoader = MySingleton.getInstance(rootView.getContext()).getImageLoader();

            // Set the URL of the image that should be loaded into this view, and
            // specify the ImageLoader that will be used to make the request.
            mNetworkImageView.setImageUrl(result.get(0).getThumbnailUrl(), mImageLoader);
        }
    }
}
