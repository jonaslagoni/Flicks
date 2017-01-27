package jonaslagoni.fliks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import java.util.ArrayList;
import java.util.List;

import jonaslagoni.fliks.BrowseRecycleView.BrowseController;
import jonaslagoni.fliks.BrowseRecycleView.BrowsePara;
import jonaslagoni.fliks.BrowseRecycleView.BrowseTextWatcher;
import jonaslagoni.fliks.BrowseRecycleView.MyAdapter;

/**
 * Created by jonas on 27-01-2017.
 */

public class BrowsePictures extends Controller {

    private RecyclerView recyclerView;

    private Context context;

    private MyAdapter recyclerView_Adapter;

    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_view);

        initRecycle();
        setData();
        ((EditText)findViewById(R.id.browse_search_input)).addTextChangedListener(new BrowseTextWatcher(this, recyclerView_Adapter));
    }

    private void setData(){
        Flickr f = new Flickr("b665313ceeefd9095f0f6bb6fcbefa57");
        PhotosInterface t = f.getPhotosInterface();
        SearchParameters search_test = new SearchParameters();
        search_test.setText("Mountain");
        new BrowseController(super.findViewById(R.id.content_menu_drawer)).execute(new BrowsePara(f, t, search_test, recyclerView_Adapter, this));
    }
    private void initRecycle(){
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_component);
        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new MyAdapter(context);

        recyclerView.setAdapter(recyclerView_Adapter);
    }
}
