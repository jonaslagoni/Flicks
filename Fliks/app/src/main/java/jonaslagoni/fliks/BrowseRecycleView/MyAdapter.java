package jonaslagoni.fliks.BrowseRecycleView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.googlecode.flickrjandroid.photos.Photo;

import java.util.ArrayList;
import java.util.List;

import jonaslagoni.fliks.R;

/**
 * Created by jonas on 27-01-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    //the list which stores all the url values
    private List<PhotoList> values_test;
    private Context context;
    //image loader used to load each of the images from url
    private ImageLoader mImageLoader;

    /**
     * Creates the adapter
     * @param context Context
     */
    public MyAdapter(Context context){
        values_test = new ArrayList();
        this.context = context;
        // Get the ImageLoader through singleton class.
        mImageLoader = MySingleton.getInstance(context).getImageLoader();
    }

    /**
     * @param parent ViewGroup
     * @param viewType int
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_single_view,parent,false);
        //create the custom viewholder
        MyViewHolder viewHolder1 = new MyViewHolder(view1);

        return viewHolder1;
    }

    /**
     * @param Vholder MyViewHolder custom
     * @param position int
     */
    @Override
    public void onBindViewHolder(MyViewHolder Vholder, int position){
        // Set the URL of the image that should be loaded into this view, and
        // specify the ImageLoader that will be used to make the request.
        Vholder.thumbnailNetworkView.setImageUrl(values_test.get(position).getThumnailUrl(), mImageLoader);
        //sets the url for this viewholder from the list
        Vholder.setUrl(values_test.get(position).getImageUrl());
    }

    /**
     * The size of the list of urls
     * @return int
     */
    @Override
    public int getItemCount(){
        return values_test.size();
    }

    /**
     * Adds a photo to the list and notify that the data have changed
     * @param photo Photo
     */
    public void addPhoto(Photo photo){
        //add the url
        values_test.add(new PhotoList(photo));
        //data has changed
        notifyDataSetChanged();
    }

    /**
     * Reset the list
     */
    public void reset(){
        values_test.clear();
    }

}