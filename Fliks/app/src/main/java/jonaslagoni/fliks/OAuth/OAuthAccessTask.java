package jonaslagoni.fliks.OAuth;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import org.json.JSONException;
import java.io.IOException;

import jonaslagoni.fliks.R;

/**
 * Created by jonas on 28-01-2017.
 */

public class OAuthAccessTask extends AsyncTask<Void, Void, Void> {
    private Flickr flickr;
    private OAuthToken oAuthToken;
    private String oauth_token;
    private String oauth_verifier;
    private Activity activity;
    public OAuthAccessTask(Flickr flickr, OAuthToken oAuthToken, String oauth_token, String oauth_verifier, Activity activity){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
        this.oauth_token = oauth_token;
        this.oauth_verifier = oauth_verifier;
        this.activity = activity;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            //Last step
            //Get the authentication
            final OAuth oAuth = flickr.getOAuthInterface().getAccessToken(oauth_token, oAuthToken.getOauthTokenSecret(), oauth_verifier);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView)activity.findViewById(R.id.username)).setText(oAuth.getUser().getUsername());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return null;
    }
}
