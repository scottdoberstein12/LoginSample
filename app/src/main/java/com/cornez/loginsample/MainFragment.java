package com.cornez.loginsample;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by scottdoberstein12 on 4/12/2016.
 */
public class MainFragment extends Fragment {

    private TextView mTextDetails;
private CallbackManager mCallbackManager;
private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>(){

    @Override
    public void onSuccess(LoginResult loginResult) {
        AccessToken accesstoken = loginResult.getAccessToken();
        Profile profile = Profile.getCurrentProfile();
        if(profile != null){
            mTextDetails.setText("Welcome " + profile.getName());
        }
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
};
public MainFragment(){

}
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container){
    return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    super.onViewCreated(view, savedInstanceState);
        LoginButton loginbutton = (LoginButton)view.findViewById(R.id.login_button);
        loginbutton.setReadPermissions("user_friends");
        loginbutton.setFragment(this);
        loginbutton.registerCallback(mCallbackManager, mCallback);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}