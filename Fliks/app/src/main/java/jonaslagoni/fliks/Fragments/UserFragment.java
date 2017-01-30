package jonaslagoni.fliks.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.googlecode.flickrjandroid.people.User;

import jonaslagoni.fliks.MenuDrawer;
import jonaslagoni.fliks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View _view = inflater.inflate(R.layout.fragment_user_details, container, false);
        final User user = ((MenuDrawer)getActivity()).getUser();
        ((TextView)_view.findViewById(R.id.details_username)).setText(user.getRealName());
        return _view;
    }

}
