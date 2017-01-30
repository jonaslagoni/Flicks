package jonaslagoni.fliks.OAuth;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.oauth.OAuthToken;

import java.io.IOException;
import java.net.URL;

import jonaslagoni.fliks.MenuDrawer;
import jonaslagoni.fliks.R;

import static com.googlecode.flickrjandroid.auth.Permission.WRITE;

/**
 * Created by jonas on 27-01-2017.
 * Used for
 */
public class OAuthController extends AsyncTask<Void, Void, URL> {
    private WebView webView;
    private Activity activity;
    private Flickr flickr;
    private OAuthToken token = null;
    private View _view;

    /**
     * AsyncTask for starting the OAuth process
     * @param activity Activity
     * @param _view View
     */
    public OAuthController(Activity activity, View _view){
        this.webView = (WebView) _view.findViewById(R.id.OAuthWebview);
        this.activity = activity;
        this._view = _view;
        //get flickr object from MenuDrawer
        flickr = ((MenuDrawer)activity).getFlickr();
    }

    /**
     * Lets do some stuff
     * @param params Void
     * @return URL
     */
    @Override
    protected URL doInBackground(Void... params) {
        String callBackUrl = "fragment_login.xml";
        URL url = null;
        try {
            //Make initial handshake with server
            token = flickr.getOAuthInterface().getRequestToken(callBackUrl);
            //build the URL for login
            url = flickr.getOAuthInterface().buildAuthenticationUrl(WRITE, token);
        } catch (IOException e) {
            Snackbar.make(_view, "Check your internet connection #1001", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } catch (FlickrException e) {
            Snackbar.make(_view, "Flickr error #1002", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        return url;
    }

    /**
     * When all done
     * @param result URL
     */
    @Override
    protected void onPostExecute(URL result) {
        //enable javascript or wont load
        webView.getSettings().setJavaScriptEnabled(true);
        //add a custom webclient for handling the last bit of verification
        WebClient webClient = new WebClient(flickr, token, activity, _view);
        webView.setWebViewClient(webClient);
        //finally load the URL the user need to use for login
        webView.loadUrl(result.toString());
    }


}
