package jonaslagoni.fliks.BrowseRecycleView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

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
    private String url;
    private final Context context;
    /**
     * Get the components used from the view
     * @param v
     */
    public MyViewHolder(View v){
        super(v);
        this.context = v.getContext();
        //get the networkview from the view and save it
        thumbnailNetworkView = (NetworkImageView) v.findViewById(R.id.ThumbnailNetworkView);
        thumbnailNetworkView.setOnClickListener(this);
    }

    @Override
    public void onClick(View vs) {
        Intent intent =  new Intent(context, FullscreenImageview.class);
        intent.putExtra("Test", url);
        context.startActivity(intent);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
