package jonaslagoni.fliks.OAuth;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.activity.Item;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import org.json.JSONException;
import java.io.IOException;

import jonaslagoni.fliks.Fragments.BrowseFragment;
import jonaslagoni.fliks.Fragments.LoginFragment;
import jonaslagoni.fliks.Fragments.UserFragment;
import jonaslagoni.fliks.MenuDrawer;
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
    private View _view;

    /**
     * Used for the final handshake with flickr server to get user data
     * @param flickr Flickr
     * @param oAuthToken OAuthToken
     * @param oauth_token String
     * @param oauth_verifier String
     * @param activity Activity
     * @param _view View
     */
    public OAuthAccessTask(Flickr flickr, OAuthToken oAuthToken, String oauth_token, String oauth_verifier, Activity activity, View _view){
        this.flickr = flickr;
        this.oAuthToken = oAuthToken;
        this.oauth_token = oauth_token;
        this.oauth_verifier = oauth_verifier;
        this.activity = activity;
        this._view = _view;
    }

    /**
     * Do the last handshake
     * @param params Void
     * @return Void
     */
    @Override
    protected Void doInBackground(Void... params) {
        try {
            //Last step
            //Get the authentication
            final OAuth oAuth = flickr.getOAuthInterface().getAccessToken(oauth_token, oAuthToken.getOauthTokenSecret(), oauth_verifier);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //remove the webview since we are done
                    activity.findViewById(R.id.OAuthWebview).setVisibility(View.GONE);
                    //set the user to the activity for lat use
                    ((MenuDrawer)activity).setUser(oAuth.getUser());

                    //lets force the user over to userinformation fragment
                    FragmentManager fn = activity.getFragmentManager();
                    fn.beginTransaction().replace(R.id.contentFrame, new UserFragment()).commit();

                    //Add and remove menu items correspondingly
                    NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
                    // get menu from navigationView
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_login).setVisible(false);
                    menu.findItem(R.id.nav_logout).setVisible(true);
                    menu.findItem(R.id.nav_user).setVisible(true);
                    menu.findItem(R.id.nav_user_browse).setVisible(true);
                }
            });
        } catch (IOException e) {
            Snackbar.make(_view, "Check your internet connection #2001", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } catch (FlickrException e) {
            Snackbar.make(_view, "Flickr error #2002", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        return null;
    }
}
