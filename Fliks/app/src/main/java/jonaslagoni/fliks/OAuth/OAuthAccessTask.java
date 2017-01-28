package jonaslagoni.fliks.OAuth;

import android.os.AsyncTask;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jonas on 28-01-2017.
 */

public class OAuthAccessTask extends AsyncTask<Void, Void, Void> {
    private Flickr flickr;
    private OAuthToken oAuthToken;
    private String oauth_token;
    private String oauth_verifier;
    public OAuthAccessTask(Flickr flickr, OAuthToken oAuthToken, String oauth_token, String oauth_verifier){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
        this.oauth_token = oauth_token;
        this.oauth_verifier = oauth_verifier;
    }
    @Override
    protected Void doInBackground(Void... params) {
        OAuth oAuth = null;
        try {
            System.out.println("oauthVerifier | " + oauth_verifier);
            System.out.println("tokenSecret | " + oauth_token);
            System.out.println("token | " + oAuthToken.getOauthTokenSecret());
            oAuth = flickr.getOAuthInterface().getAccessToken( oAuthToken.getOauthToken(),oauth_token, oauth_verifier);
            System.out.println(oAuth.getUser().getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return null;
    }
}
