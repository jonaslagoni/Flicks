package jonaslagoni.fliks;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.people.User;

import jonaslagoni.fliks.Fragments.BrowseFragment;
import jonaslagoni.fliks.Fragments.LoginFragment;
import jonaslagoni.fliks.Fragments.UserBrowseFragment;
import jonaslagoni.fliks.Fragments.UserFragment;
import jonaslagoni.fliks.Fragments.mainFragment;

public class MenuDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // The user state, changes when logedin
    private User user = new User();
    // Using same flickr object throughout project
    private Flickr flickr = new Flickr("b665313ceeefd9095f0f6bb6fcbefa57", "9ff48e279a496c8d");

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // Do whatever you want here
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the first Fragment
        FragmentManager fn = getFragmentManager();
        fn.beginTransaction().replace(R.id.contentFrame, new mainFragment()).commit();

    }

    /**
     * When back button is pressed either close drawer or ...
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * @param menu Menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    /**
     * @param item MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * On selected item do the following
     * @param item MenuItem
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //get the fragmentManager
        FragmentManager fn = getFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_browse) {
            // set browsing fragment to search for public pictures
            fn.beginTransaction().replace(R.id.contentFrame, new BrowseFragment()).commit();
        }else if(id == R.id.nav_login) {
            // set login fragment to login
            fn.beginTransaction().replace(R.id.contentFrame, new LoginFragment()).commit();
        }else if(id == R.id.nav_home) {
            // set frontpage fragment
            fn.beginTransaction().replace(R.id.contentFrame, new mainFragment()).commit();
        }else if(id == R.id.nav_logout){
            //lets logout and handle the menu items correspondingly
            logout();
            NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
            // get menu from navigationView
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);
            menu.findItem(R.id.nav_user).setVisible(false);
            menu.findItem(R.id.nav_user_browse).setVisible(false);
            // Set frontpage fragment when logged out
            fn.beginTransaction().replace(R.id.contentFrame, new mainFragment()).commit();
        }else if(id == R.id.nav_user_browse) {
            // Set the userbrowse fragment to see your own pictures
            fn.beginTransaction().replace(R.id.contentFrame, new UserBrowseFragment()).commit();
        }else if(id == R.id.nav_user) {
            // Set the user info fragment
            fn.beginTransaction().replace(R.id.contentFrame, new UserFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sets a new user
     * @param user User to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the current user associated
     */
    public User getUser(){
        return user;
    }

    /**
     * @return flickr object
     */
    public Flickr getFlickr() {
        return flickr;
    }

    /**
     * Lets logout
     * @return logout correctly?
     */
    public boolean logout(){
        user = null;
        return true;
    }


}
