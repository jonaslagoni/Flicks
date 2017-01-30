package jonaslagoni.fliks.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jonaslagoni.fliks.OAuth.OAuthController;
import jonaslagoni.fliks.R;

/**
 * Created by jonas on 28-01-2017.
 */

public class LoginFragment extends Fragment{
    public LoginFragment(){
        // Required empty public constructor
    }

    /**
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initialOauth(rootView);
        return rootView;
    }
    public void initialOauth(View _view) {
        new OAuthController(getActivity(), _view).execute();
    }

}
