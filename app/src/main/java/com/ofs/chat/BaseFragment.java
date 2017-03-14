package com.ofs.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ofs.chat.contract.AbstractActivityCallback;
import com.ofs.chat.contract.AbstractFragmentCallback;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class BaseFragment extends Fragment implements AbstractFragmentCallback,AbstractActivityCallback {

    AbstractFragmentCallback fragmentCallback;
    AbstractActivityCallback activityCallback;

    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseUser user;
    protected FirebaseDatabase firebaseDatabase;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                }else {

                }
            }
        };
        try{
            fragmentCallback = (AbstractFragmentCallback) getActivity();
            activityCallback = (AbstractActivityCallback) getActivity();
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void startActivity(Class<? extends AppCompatActivity> claz, Bundle args) {
        activityCallback.startActivity(claz,args);
    }

    @Override
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackstack, Bundle args) {
        fragmentCallback.replaceFragment(getActivity().getSupportFragmentManager(),fragment,addToBackstack,args);
    }
}
