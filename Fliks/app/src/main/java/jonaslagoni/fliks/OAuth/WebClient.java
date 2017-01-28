package jonaslagoni.fliks.OAuth;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

import java.io.IOException;

import jonaslagoni.fliks.R;

/**
 * Created by jonas on 28-01-2017.
 */

public class WebClient extends WebViewClient {
    private Flickr flickr;
    private OAuthToken oAuthToken;

    public WebClient(Flickr flickr, OAuthToken oAuthToken){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.split("\\?").length != 0){
            System.out.println(url.split("\\?")[0]);
            if(url.split("\\?")[0].equals("https://m.flickr.com/activity_browser_view.xml")){
                String oauth_token = url.split("\\?")[1].split("&")[0].split("=")[1];
                String oauth_verifier =  url.split("\\?")[1].split("&")[1].split("=")[1];
                new OAuthAccessTask(flickr, oAuthToken, oauth_token, oauth_verifier).execute();
                view.setVisibility(View.GONE);
            }
        }
        view.loadUrl(url);
        System.out.println(url);

        return false;
    }
}
