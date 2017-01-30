package jonaslagoni.fliks.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.people.User;
import com.googlecode.flickrjandroid.photos.SearchParameters;

import jonaslagoni.fliks.BrowseRecycleView.BrowseController;
import jonaslagoni.fliks.BrowseRecycleView.BrowsePara;
import jonaslagoni.fliks.BrowseRecycleView.MyAdapter;
import jonaslagoni.fliks.MenuDrawer;
import jonaslagoni.fliks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserBrowseFragment extends Fragment {
    private MyAdapter recyclerView_Adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private RecyclerView recyclerView;
    private Flickr flickr;
    private User user;
    private Context context;
    public UserBrowseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();

        // Required empty public constructor
        flickr = ((MenuDrawer)getActivity()).getFlickr();
        user = ((MenuDrawer)getActivity()).getUser();
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_user_browse, container, false);
        initRecycle(_view);
        getPictures(_view);
        return _view;
    }


    private void getPictures(View view){
        SearchParameters search_test = new SearchParameters();
        search_test.setUserId(user.getId());
        new BrowseController(view).execute(new BrowsePara(flickr, search_test, recyclerView_Adapter, getActivity()));
    }
    private void initRecycle(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_component);
        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new MyAdapter(context);

        recyclerView.setAdapter(recyclerView_Adapter);
    }
}
