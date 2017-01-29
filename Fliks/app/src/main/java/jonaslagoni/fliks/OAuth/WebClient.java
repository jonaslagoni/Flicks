package jonaslagoni.fliks.OAuth;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

import jonaslagoni.fliks.R;

/**
 * Created by jonas on 28-01-2017.
 */

public class WebClient extends WebViewClient {
    private Flickr flickr;
    private OAuthToken oAuthToken;
    private Activity activity;
    public WebClient(Flickr flickr, OAuthToken oAuthToken, Activity activity){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.split("\\?").length != 0){
            System.out.println(url.split("\\?")[0]);
            if(url.split("\\?")[0].equals("https://m.flickr.com/fragment_login.xml")){
                String oauth_token = url.split("\\?")[1].split("&")[0].split("=")[1];
                String oauth_verifier =  url.split("\\?")[1].split("&")[1].split("=")[1];
                new OAuthAccessTask(flickr, oAuthToken, oauth_token, oauth_verifier, activity).execute();
                view.setVisibility(View.GONE);
            }
        }
        view.loadUrl(url);
        return false;
    }
}
