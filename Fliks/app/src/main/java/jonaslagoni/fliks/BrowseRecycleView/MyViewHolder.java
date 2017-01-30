package jonaslagoni.fliks.BrowseRecycleView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

import jonaslagoni.fliks.FullscreenImageview;
import jonaslagoni.fliks.R;

/**
 * Created by jonas on 27-01-2017.
 *
 * This class is used for holding the information regarding the components
 * for a single viewholder in the recycleview.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //the NetworkImageView this component use
    public NetworkImageView thumbnailNetworkView;
    //the url for the picture this viewholder uses
    private String url;
    private Context context;

    /**
     * Get the components used from the view
     * @param v View
     */
    public MyViewHolder(View v){
        super(v);
        this.context = v.getContext();
        //get the networkview from the view and save it
        thumbnailNetworkView = (NetworkImageView) v.findViewById(R.id.ThumbnailNetworkView);
        //set a clicklistener for when the user want to view it fullscreen
        thumbnailNetworkView.setOnClickListener(this);
    }

    /**
     * When this is clicked
     * @param vs View
     */
    @Override
    public void onClick(View vs) {
        //Show the fullscreen and add the extra for url
        Intent intent = new Intent(context, FullscreenImageview.class);
        intent.putExtra("Test", url);
        context.startActivity(intent);
    }

    /**
     * @return String, the url for the picture
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url for the picture
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
