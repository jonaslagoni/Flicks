package jonaslagoni.fliks.OAuth;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
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
    private View _view;

    /**
     * Custom webclient for last bit of login
     * @param flickr Flickr
     * @param oAuthToken OAuthToken
     * @param activity Activity
     * @param _view View
     */
    public WebClient(Flickr flickr, OAuthToken oAuthToken, Activity activity, View _view){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
        this.activity = activity;
        this._view = _view;
    }

    /**
     * When a page finishes loading
     * @param view WebView
     * @param url String
     */
    public void onPageFinished(WebView view, String url) {
        //check if we are done with login already
        if(view.getVisibility() != View.GONE) {
            //if not remove the spinner and text
            activity.findViewById(R.id.FragmentLoading).setVisibility(View.GONE);
            activity.findViewById(R.id.FragmentLoadingText).setVisibility(View.GONE);
        }
    }

    /**
     * When URL should be loaded do something extra
     * @param view WebView
     * @param url String
     * @return boolean
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //check if we are done with login already
        if(view.getVisibility() != View.GONE){
            //Show the spinner and text while we load the page
            activity.findViewById(R.id.FragmentLoading).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.FragmentLoadingText).setVisibility(View.VISIBLE);
            //check if we got the correct end link
            if(url.split("\\?").length != 0){
                if(url.split("\\?")[0].equals("https://m.flickr.com/fragment_login.xml")){
                    //when we do get the necessary data from the URL
                    String oauth_token = url.split("\\?")[1].split("&")[0].split("=")[1];
                    String oauth_verifier =  url.split("\\?")[1].split("&")[1].split("=")[1];
                    //now do the final handshake with the flickr server for retrieving the user data
                    new OAuthAccessTask(flickr, oAuthToken, oauth_token, oauth_verifier, activity, _view).execute();
                }
            }
            view.loadUrl(url);
        }
        return false;
    }
}
